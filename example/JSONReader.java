package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class JSONReader {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static List readProducts() throws IOException {
        File file = new File("products.json");
        List<Product> productList = objectMapper.readValue(file, new TypeReference<>(){});
        return productList;
    }

    static List readBosses() throws IOException {
        File file = new File("bosses.json");
        List<Boss> bossList = objectMapper.readValue(file, new TypeReference<>(){});
        return bossList;
    }

    static List readCells() throws IOException {
        File file = new File("cells.json");
        List<Cell> cellList = objectMapper.readValue(file, new TypeReference<>(){});
        return cellList;
    }

    static List<LinkedList<Cell>> readCellsByStorageIds() throws IOException {
        File file = new File("cells.json");
        List<Cell> cellList = objectMapper.readValue(file, new TypeReference<>(){});
        List<LinkedList<Cell>> cellsByStorageIds = new ArrayList<>();
        for (int i = 0; i < Storage.getCurrentId(); i++){
            cellsByStorageIds.add(new LinkedList<>());
        }
        for (Cell cell: cellList){
            int storageId = cell.getStorageId();
            cellsByStorageIds.get(storageId-1).add(cell);
        }
        return cellsByStorageIds;
    }

    static List readEmployees() throws IOException {
        File file = new File("employees.json");
        List<Employee> employeeList = objectMapper.readValue(file, new TypeReference<>(){});
        return employeeList;
    }

    static List readSalepoints() throws IOException {
        File file = new File("salepoints.json");
        List<Salepoint> salepointList = objectMapper.readValue(file, new TypeReference<>(){});
        return salepointList;
    }

    static List readWarehouses() throws IOException {
        File file = new File("warehouses.json");
        List<Warehouse> warehouseList = objectMapper.readValue(file, new TypeReference<>(){});
        return warehouseList;
    }

    static List readConsumers() throws IOException {
        File file = new File("consumers.json");
        List<Consumer> consumerList = objectMapper.readValue(file, new TypeReference<>(){});
        return consumerList;
    }

    static List readBaskets() throws IOException {
        File file = new File("baskets.json");
        List<Basket> basketList = objectMapper.readValue(file, new TypeReference<>(){});
        return basketList;
    }

    static List readOrders() throws IOException {
        File file = new File("orders.json");
        List<Order> orderList = objectMapper.readValue(file, new TypeReference<>(){});
        return orderList;
    }

    public static Consumer getConsumerByFullname(String fullname) throws IOException {
        List<Consumer> consumerList = readConsumers();
        for (Consumer consumer: consumerList){
            if (consumer.getFullname().equals(fullname)){
                return consumer;
            }
        }
        return null;
    }

    public static Boss getBossByFullname(String fullname) throws IOException {
        List<Boss> list = readBosses();
        for (Boss boss: list){
            if (boss.getFullName().equals(fullname)){
                return boss;
            }
        }
        return null;
    }

    public static Salepoint getSalepointByCity(String city) throws IOException {
        List<Salepoint> list = readSalepoints();
        for (Salepoint salepoint: list){
            if (salepoint.getCity().equals(city)){
                return salepoint;
            }
        }
        return null;
    }

    public static Product getProductById(int poductId) throws IOException {
        List<Product> list = readProducts();
        for (Product product: list){
            if (product.getId() == poductId){
                return product;
            }
        }
        return null;
    }

    public static Basket getBasketById(int basketId) throws IOException {
        List<Basket> list = readBaskets();
        for (Basket basket: list){
            if (basket.getId() == basketId){
                return basket;
            }
        }
        return null;
    }

    public static Employee getEmployeeById(int employeeId) throws IOException {
        List<Employee> list = readEmployees();
        for (Employee employee: list){
            if (employee.getId() == employeeId){
                return employee;
            }
        }
        return null;
    }

    public static Order getOrderById(int orderId) throws IOException {
        List<Order> list = readOrders();
        for (Order order: list){
            if (order.getId() == orderId){
                return order;
            }
        }
        return null;
    }

    public static Warehouse getWarehouseById(int warehouseId) throws IOException {
        List<Warehouse> list = readWarehouses();
        for (Warehouse warehouse: list){
            if (warehouse.getId() == warehouseId){
                return warehouse;
            }
        }
        return null;
    }

    public static Storage getStorageById(int storageId) throws IOException {
        List<Storage> list = readWarehouses();
        list.addAll(readSalepoints());
        for (Storage storage: list){
            if (storage.getId() == storageId){
                return storage;
            }
        }
        return null;
    }

    public static int getNumberOfConsumers() throws IOException {
        try {
            return readConsumers().size();
        } catch (IOException e) {
            return 0;
        }
    }

    public static int getNumberOfStorages() throws IOException {
        try {
            List<Storage> list = readSalepoints();
            list.addAll(readWarehouses());
            return list.size();
        } catch (IOException e) {
            return 0;
        }
    }

    public static int getNumberOfEmployees() throws IOException {
        try {
            return readEmployees().size();
        } catch (IOException e) {
            return 0;
        }
    }

    public static int getNumberOfOrders() throws IOException {
        try {
            return readOrders().size();
        } catch (IOException e) {
            return 0;
        }
    }

    public static int getNumberOfCells() throws IOException {
        try {
            return readCells().size();
        } catch (IOException e) {
            return 0;
        }
    }

    public static int getProductQuantityById(int productId) throws IOException {
        List<Integer> warehouseIds = new ArrayList<>();
        List<Warehouse> warehouses = JSONReader.readWarehouses();
        int quantity = 0;
        for (Warehouse warehouse: warehouses){
            warehouseIds.add(warehouse.getId());
        }
        List<Cell> cells = JSONReader.readCells();
        for (Cell cell: cells){
            if (warehouseIds.contains(cell.getStorageId()) && cell.getProductId() == productId) {
                quantity++;
            }
        }
        return quantity;
    }

    public static Boss getBossByStorageId(int storageId) throws IOException {
        List<Boss> bossList = readBosses();
        for (Boss boss: bossList){
            if (boss.getStorageId() == storageId){
                return boss;
            }
        }
        return null;
    }
}
