package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Salepoint extends Storage{
    private int profit;

    public Salepoint() {
        super();
        this.profit = 0;
    }

    public Salepoint(String city, int capasity) {
        super(city, capasity);
        this.profit = 0;
    }

    public Salepoint(int id, String city, int capasity) {
        super(id, city, capasity);
    }

    public Salepoint(int id, String city, int capasity, int fullCellsNumber) {
        super(id, city, capasity, fullCellsNumber);
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public static Salepoint create(String city, int capasity) throws FileNotFoundException {
        Salepoint salepoint = new Salepoint(city, capasity);
        createNewEmptyCells(capasity, salepoint.getId());
        JSONWriter.saveNewSalepoint(salepoint);
        currentId++;
        return salepoint;
    }

    public static Salepoint create(int id, String city, int capasity) throws FileNotFoundException {
        Salepoint salepoint = new Salepoint(id, city, capasity);
        createNewEmptyCells(capasity, salepoint.getId());
        JSONWriter.saveNewSalepoint(salepoint);
        currentId++;
        return salepoint;
    }

    public static Salepoint create(int id, String city, int capasity, int fullCellsNumber) throws FileNotFoundException {
        Salepoint salepoint = new Salepoint(id, city, capasity, fullCellsNumber);
        JSONWriter.saveNewSalepoint(salepoint);
        currentId++;
        return salepoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void increaseProfit(int plus){
        this.profit += plus;
    }

    public void addProduct(int productId) throws IOException {
        Cell foundCell = getEmptyCell();
        if (foundCell != null){
            foundCell.addProductToCell(productId);
            setEmptyCellsNumber(getEmptyCellsNumber()-1);
            setFullCellsNumber(getFullCellsNumber() + 1);
            JSONWriter.saveModifiedSalepoint(this);
        }
    }

    @Override
    public void printInformation() throws IOException {
        super.printInformation();
        System.out.println("☆☆☆☆ Прибыль: " +  profit + " ☆☆☆☆");
        System.out.println("=".repeat(111));
    }

    @Override
    public void printProducts() throws IOException {
        int productId = 0;
        int currentNumberOfProducts = 0;
        List<Integer> numberOfProductsById = getAllProductNumberByStorageId();
        if (Collections.frequency(numberOfProductsById, 0) != numberOfProductsById.size()){
            System.out.printf("%-40s | %16s | %16s | %10s%n", "Товар", "id", "Цена", "Количество");
            System.out.println("=".repeat(90));
            for (int i = 1; i < 11; i++){ //печатаем товары в наличии
                if (numberOfProductsById.get(i) != 0){
                    Product product = JSONReader.getProductById(i);
                    System.out.printf(
                            "%-40s | %16d | %16d | %10d%n",
                            "☆ " + product.getName(),
                            product.getId(),
                            product.getPriceForConsumer(),
                            numberOfProductsById.get(i));
                }
            }
        } else {
            System.out.println("Товары закончились");
        }
    }

    public void purchase(int productId, int quantity, Warehouse warehouse) throws IOException {
        LinkedList<Cell> salepointCells = JSONReader.readCellsByStorageIds().get(id-1);
        LinkedList<Cell> warehouseCells = JSONReader.readCellsByStorageIds().get(warehouse.getId()-1);
        ListIterator<Cell> salepointIterator = salepointCells.listIterator();
        int currentQuantity = 0;
        while (salepointIterator.hasNext() && currentQuantity < quantity){
            Cell cell = salepointIterator.next();
            if (cell.isEmpty()){
                cell.setProductId(productId);
                salepointIterator.set(cell);
                currentQuantity++;
            }
        }
        currentQuantity = 0;
        ListIterator<Cell> warehouseIterator = warehouseCells.listIterator();
        while (warehouseIterator.hasNext() && currentQuantity < quantity){
            Cell cell = warehouseIterator.next();
            if (cell.getProductId() == productId){
                cell.setProductId(0);
                cell.setEmpty(true);
                warehouseIterator.set(cell);
                currentQuantity++;
            }
        }
        Product product = JSONReader.getProductById(productId);
        increaseRevenue(product.getPriceForSalePoint() * (-1) * quantity);
        increaseProfit(product.getPriceForSalePoint() * (-1) * quantity);
        increaseFullCellNumber(quantity);
        increaseEmptyCellNumber((-1) * quantity);
        warehouse.increaseRevenue(product.getPriceForSalePoint() * quantity);
        warehouse.increaseFullCellNumber((-1) * quantity);
        warehouse.increaseEmptyCellNumber(quantity);
        JSONWriter.saveModifiedCellsInStorage(salepointCells);
        JSONWriter.saveModifiedCellsInStorage(warehouseCells);
        JSONWriter.saveModifiedSalepoint(this);
        JSONWriter.saveModifiedWarehouse(warehouse);
    }

    /**public void purchase(int productId, int quantity) throws IOException {
        List<Cell> newCells = JSONReader.readCells();
        List<Cell> prevCells = JSONReader.readCells();
        Iterator<Cell> iterator = newCells.iterator();
        Product product = JSONReader.getProductById(productId);
        int currentQuantity = 0;
        while (iterator.hasNext() && currentQuantity < quantity){
            Cell cell = iterator.next();
            Storage storage = JSONReader.getStorageById(cell.getStorageId());
            if (cell.getProductId() == productId && storage instanceof Warehouse){
                Warehouse warehouse = (Warehouse) storage;
                cell.setProductId(0);
                cell.setEmpty(true);
                Cell emptyCell = this.getEmptyCell();
                emptyCell.setProductId(productId);
                this.increaseEmptyCellNumber(-1);
                this.increaseFullCellNumber(1);
                warehouse.increaseEmptyCellNumber(1);
                warehouse.increaseFullCellNumber(-1);
                warehouse.increaseRevenue(product.getPriceForSalePoint());
                JSONWriter.saveModifiedCell(cell);
                JSONWriter.saveModifiedCell(emptyCell);
                JSONWriter.saveModifiedWarehouse(warehouse);
                currentQuantity++;
            }
        }
        this.increaseRevenue(product.getPriceForSalePoint() * (-1) * quantity);
        this.increaseProfit(product.getPriceForSalePoint() * (-1) * quantity);
        JSONWriter.saveModifiedSalepoint(this);
    }**/

    @Override
    public void close() throws IOException {
        super.close();
        JSONWriter.deleteSalepoint(this);
    }
}
