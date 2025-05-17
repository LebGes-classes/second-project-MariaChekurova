package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
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

    public static Order getOrderById(int orderId) throws IOException {
        List<Order> list = readOrders();
        for (Order order: list){
            if (order.getId() == orderId){
                return order;
            }
        }
        return null;
    }

    public static Cell getCellById(int cellId) throws IOException {
        List<Cell> list = readCells();
        for (Cell cell: list){
            if (cell.getId() == cellId){
                return cell;
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
}
