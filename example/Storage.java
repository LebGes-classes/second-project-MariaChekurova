package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Storage {
    protected static int globalId = 1;
    protected int id;
    protected String city;
    protected int capacity;
    protected int emptyCellsNumber;
    protected int fullCellsNumber;
    protected int revenue; //выручка


    public Storage(int id, String city, int capacity) {
        this.id = id;
        this.city = city;
        this.capacity = capacity;
        this.emptyCellsNumber = capacity;
        this.fullCellsNumber = 0;
        this.revenue = 0;
        globalId++;
    }

    public Storage(int id, String city, int capacity, int fullCellsNumber) {
        this.id = id;
        this.city = city;
        this.capacity = capacity;
        this.fullCellsNumber = fullCellsNumber;
        this.emptyCellsNumber = capacity - fullCellsNumber;
        this.revenue = 0;
        globalId++;
    }

    public Storage(String city, int capasity) {
        this.city = city;
        this.capacity = capasity;
        this.id = globalId++;
        this.emptyCellsNumber = capasity;
        this.fullCellsNumber = 0;
    }

    public Storage() {
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public void printProducts() throws IOException {
        List<Cell> cells = JSONReader.readCells();
        int productId = 0;
        int currentNumberOfProducts = 0;
        List<Integer> numberOfProductsById = getAllProductNumberByStorageId();
        if (Collections.frequency(numberOfProductsById, 0) != numberOfProductsById.size()){
            System.out.printf("%-32s | %16d | %16d | %10.2f%n", "Товар", "id", "Цена", "Количество");
            for (int i = 1; i < 11; i++){ //печатаем товары в наличии
                if (numberOfProductsById.get(i) != 0){
                    Product product = Product.getProductById(i);
                    System.out.printf(
                            "%-32s | %16d | %16d | %10.2f%n",
                            "☆ " + product.getName(),
                            product.getId(),
                            product.getPriceForConsumer(),
                            i);
                }
            }
        } else {
            System.out.println("Товары закончились");
        }
    }

    public List<Integer> getAllProductNumberByStorageId () throws IOException {
        List<Cell> cells = JSONReader.readCells();
        int productId = 0;
        int currentNumberOfProducts = 0;
        List<Integer> numberOfProductsById = new ArrayList<>(Collections.nCopies(15, 0));
        for (Cell cell: cells) {//считаем количество каждого товара на пункте продаж
            if (cell.getStorageId() == id){
                productId = cell.getProductId();
                currentNumberOfProducts = numberOfProductsById.get(productId) + 1;
                numberOfProductsById.set(productId, currentNumberOfProducts);
            }
        }
        return numberOfProductsById;
    }

    protected void increaseRevenue(int plus){
        revenue += plus;
    }

    public void reduceProductById(int productId) throws IOException { //убрать товар из Storage
        List<Cell> cells = JSONReader.readCells();
        for (Cell cell: cells){
            if (cell.getStorageId() == id && cell.getProductId() == productId){
                cell.setEmpty(true);
                emptyCellsNumber++;
                fullCellsNumber--;
                JSONWriter.saveModifiedCell(cell);
            }
        }
    }

    public static void createNewEmptyCells(int cellsNumber) throws FileNotFoundException {
        for (int i = 0; i < cellsNumber; i++){
            Cell cell = Cell.create();
            JSONWriter.saveNewCell(cell);
        }
    }
}
