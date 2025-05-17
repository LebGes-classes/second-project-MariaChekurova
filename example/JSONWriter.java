package org.example;

import com.google.gson.Gson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONWriter {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void saveNewEmployee(Employee employee) throws IOException {
        Gson gson = new Gson();
        File file = new File("employees.json");
        Type listType = new TypeToken<List<Employee>>(){}.getType();
        List<Employee> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(employee);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        }
    }

    public static void saveNewBoss(Boss boss) throws IOException {
        Gson gson = new Gson();
        File file = new File("bosses.json");
        Type listType = new TypeToken<List<Boss>>(){}.getType();
        List<Boss> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(boss);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        }
    }

    public static void saveNewProduct(Product product) throws IOException {
        Gson gson = new Gson();
        File file = new File("products.json");
        Type listType = new TypeToken<List<Product>>(){}.getType();
        List<Product> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(product);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        }
    }

    public static void saveNewBasket(Basket basket) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("baskets.json");
        Type listType = new TypeToken<List<Basket>>(){}.getType();
        List<Basket> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(basket);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveNewConsumer(Consumer consumer) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("consumers.json");
        Type listType = new TypeToken<List<Consumer>>(){}.getType();
        List<Consumer> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(consumer);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveNewOrder(Order order) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("orders.json");
        Type listType = new TypeToken<List<Order>>(){}.getType();
        List<Order> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(order);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveNewSalepoint(Salepoint salepoint) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("salepoints.json");
        Type listType = new TypeToken<List<Salepoint>>(){}.getType();
        List<Salepoint> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(salepoint);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveNewCell(Cell cell) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("cells.json");
        Type listType = new TypeToken<List<Cell>>(){}.getType();
        List<Cell> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(cell);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveNewWarehouse(Warehouse warehouse) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("warehouses.json");
        Type listType = new TypeToken<List<Warehouse>>(){}.getType();
        List<Warehouse> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(warehouse);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveModifiedBasket(Basket basket) throws IOException {
        Gson gson = new Gson();
        File file = new File("baskets.json");
        Type listType = new TypeToken<List<Basket>>(){}.getType();
        List<Basket> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Basket prevBasket = JSONReader.getBasketById(basket.getId());
        objects.remove(prevBasket);
        objects.add(basket);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveModifiedCell(Cell cell) throws IOException {
        Gson gson = new Gson();
        File file = new File("cells.json");
        Type listType = new TypeToken<List<Cell>>(){}.getType();
        List<Cell> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Cell prevCell = JSONReader.getCellById(cell.getId());
        objects.remove(prevCell);
        objects.add(cell);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveModifiedConsumer(Consumer consumer) throws IOException {
        Gson gson = new Gson();
        File file = new File("consumers.json");
        Type listType = new TypeToken<List<Consumer>>(){}.getType();
        List<Consumer> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Consumer prevConsumer = JSONReader.getConsumerByFullname(consumer.getFullname());
        objects.remove(prevConsumer);
        objects.add(consumer);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveModifiedOrder(Order order) throws IOException {
        Gson gson = new Gson();
        File file = new File("orders.json");
        Type listType = new TypeToken<List<Order>>(){}.getType();
        List<Order> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Order prevOrder = JSONReader.getOrderById(order.getId());
        objects.remove(prevOrder);
        objects.add(order);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveModifiedSalepoint(Salepoint salepoint) throws IOException {
        Gson gson = new Gson();
        File file = new File("salepoints.json");
        Type listType = new TypeToken<List<Salepoint>>(){}.getType();
        List<Salepoint> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Salepoint prevSalepoint = JSONReader.getSalepointByCity(salepoint.getCity());
        objects.remove(prevSalepoint);
        objects.add(salepoint);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveModifiedWarehouse(Warehouse warehouse) throws IOException {
        Gson gson = new Gson();
        File file = new File("warehouses.json");
        Type listType = new TypeToken<List<Warehouse>>(){}.getType();
        List<Warehouse> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Warehouse prevWarehouse = JSONReader.getWarehouseById(warehouse.getId());
        objects.remove(prevWarehouse);
        objects.add(warehouse);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sdeleteSalepoint(Salepoint salepoint) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("salepoints.json");
        Type listType = new TypeToken<List<Salepoint>>(){}.getType();
        List<Salepoint> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.remove(salepoint);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteWarehouse(Warehouse warehouse) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("warehouses.json");
        Type listType = new TypeToken<List<Warehouse>>(){}.getType();
        List<Warehouse> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.add(warehouse);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteBoss(Boss boss) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("bosses.json");
        Type listType = new TypeToken<List<Boss>>(){}.getType();
        List<Basket> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.remove(boss);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
