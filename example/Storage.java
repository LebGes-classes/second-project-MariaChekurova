package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Storage {
    protected static int currentId;

    static {
        try {
            currentId = JSONReader.getNumberOfStorages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected int id;
    protected String city;
    protected int capacity;
    protected int emptyCellsNumber;
    protected int fullCellsNumber;
    protected int revenue; //выручка

    public Storage() {
        this.id = 0;
        this.city = null;
        this.capacity = 0;
        this.emptyCellsNumber = 0;
        this.fullCellsNumber = 0;
        this.revenue = 0;
    }

    public Storage(int id, String city, int capacity) {
        this.id = id;
        this.city = city;
        this.capacity = capacity;
        this.emptyCellsNumber = capacity;
        this.fullCellsNumber = 0;
        this.revenue = 0;
    }

    public Storage(int id, String city, int capacity, int fullCellsNumber) {
        this.id = id;
        this.city = city;
        this.capacity = capacity;
        this.fullCellsNumber = fullCellsNumber;
        this.emptyCellsNumber = capacity - fullCellsNumber;
        this.revenue = 0;
    }

    public Storage(String city, int capasity) {
        this.city = city;
        this.capacity = capasity;
        this.id = currentId+1;
        this.emptyCellsNumber = capasity;
        this.fullCellsNumber = 0;
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

    public int getEmptyCellsNumber() {
        return emptyCellsNumber;
    }

    public void setEmptyCellsNumber(int emptyCellsNumber) {
        this.emptyCellsNumber = emptyCellsNumber;
    }

    public int getFullCellsNumber() {
        return fullCellsNumber;
    }

    public void setFullCellsNumber(int fullCellsNumber) {
        this.fullCellsNumber = fullCellsNumber;
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

    public static int getCurrentId() {
        return currentId;
    }

    public List<Integer> getAllProductNumberByStorageId () throws IOException {
        List<Cell> cells = JSONReader.readCells();
        int productId = 0;
        int currentNumberOfProducts = 0;
        List<Integer> numberOfProductsById = new ArrayList<>(Collections.nCopies(15, 0));
        for (Cell cell: cells) {//считаем количество каждого товара на пункте продаж
            if (cell.getStorageId() == id && cell.getProductId() != 0){
                productId = cell.getProductId();
                currentNumberOfProducts = numberOfProductsById.get(productId) + 1;
                numberOfProductsById.set(productId, currentNumberOfProducts);
            }
        }
        return numberOfProductsById;
    }

    protected void increaseRevenue(int plus){
        this.revenue += plus;
    }

    protected void increaseFullCellNumber(int plus){
        this.fullCellsNumber += plus;
    }

    protected void increaseEmptyCellNumber(int plus){
        this.emptyCellsNumber += plus;
    }

    public void reduceProductById(int productId) throws IOException { //убрать товар из Storage
        List<Cell> cells = JSONReader.readCells();
        for (Cell cell: cells){
            if (cell.getStorageId() == id && cell.getProductId() == productId){
                cell.setEmpty(true);
                cell.setProductId(0);
                emptyCellsNumber++;
                fullCellsNumber--;
                JSONWriter.saveModifiedCell(cell);
                break;
            }
        }
    }

    public void printInformation() throws IOException {
        printProducts();
        System.out.println("☆☆☆☆ Выручка: " +  revenue + " ☆☆☆☆");
    }

    public void printProducts() throws IOException {
        int productId = 0;
        int currentNumberOfProducts = 0;
        List<Integer> numberOfProductsById = getAllProductNumberByStorageId();
        System.out.println("☆☆☆☆ Город: " + city + " ☆☆☆☆");
        if (Collections.frequency(numberOfProductsById, 0) != numberOfProductsById.size()){
            System.out.printf("%-40s | %16s | %16s | %16s | %10s%n", "Товар", "id", "Цена закупки", "Цена для клиента", "Количество");
            System.out.println("=".repeat(111));
            for (int i = 1; i < 11; i++){ //печатаем товары в наличии
                if (numberOfProductsById.get(i) != 0){
                    Product product = JSONReader.getProductById(i);
                    System.out.printf(
                            "%-40s | %16d | %16d | %16d | %10d%n",
                            "☆ " + product.getName(),
                            product.getId(),
                            product.getPriceForSalePoint(),
                            product.getPriceForConsumer(),
                            numberOfProductsById.get(i));
                }
            }
        } else {
            System.out.println("Товары закончились");
        }
        System.out.println("=".repeat(111));
    }

    public static void createNewEmptyCells(int cellsNumber, int storageId) throws FileNotFoundException {
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < cellsNumber; i++) {
            cells.add(new Cell(storageId));
        }
        JSONWriter.saveNewCells(cells);
    }

    public Cell getEmptyCell() throws IOException {
        LinkedList<Cell> cells = JSONReader.readCellsByStorageIds().get(id-1);
        for (Cell cell: cells){
            if (cell.getStorageId() == id && cell.isEmpty()){
                return cell;
            }
        }
        return null;
    }

    public void printEmployees() throws IOException {
        List<Employee> list = JSONReader.readEmployees();
        System.out.printf("%-30s | %16s |  %16s%n", "ФИО сотрудника", "id", "Заработная плата");
        for (Employee employee: list){
            if (employee.getStorageId() == id){
                System.out.printf("%-30s | %16d |  %16d%n", employee.getFullName(), employee.getId(), employee.getSalary());
            }
        }
        System.out.println("=".repeat(69));
    }

    public void close() throws IOException {
        Boss boss = JSONReader.getBossByStorageId(id);
        JSONWriter.deleteAllCellsByStorageId(id);
        JSONWriter.deleteAllEmployeesByStorageId(id);
        JSONWriter.deleteBoss(boss);
    }

    public void changeBoss() throws IOException {
        Scanner sc = new Scanner(System.in);
        printEmployees();
        System.out.println("Кто из сотрудников станет новым начальником? (Ведите id сотрудника)");
        int newBossId = sc.nextInt();
        Employee newBoss = JSONReader.getEmployeeById(newBossId);
        JSONWriter.deleteEmployee(newBoss);
        Boss.create(newBoss.getFullName(), id);
    }
}
