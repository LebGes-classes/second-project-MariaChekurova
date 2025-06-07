package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JSONWriter {

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

    public static void saveNewCells(List<Cell> cells) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("cells.json");
        Type listType = new TypeToken<List<Cell>>(){}.getType();
        List<Cell> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.addAll(cells);
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
        Basket prev = null;
        for (Basket basket1: objects){
            if (basket1.getId() == basket.getId()){
                prev = basket1;
                break;
            }
        }
        objects.remove(prev);
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
        Cell prevCell = null;
        for (Cell cell1: objects){
            if (cell1.getId() == cell.getId()){
                prevCell = cell1;
                break;
            }
        }
        objects.remove(prevCell);
        objects.add(cell);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveModifiedCellsInStorage(LinkedList<Cell> cells) throws IOException {
        int storageId = cells.get(0).getStorageId();
        Gson gson = new Gson();
        File file = new File("cells.json");
        List<LinkedList<Cell>> allCellsByStorage = JSONReader.readCellsByStorageIds();
        allCellsByStorage.set(storageId-1, cells);
        List<Cell> objects = allCellsByStorage.stream()
                .flatMap(LinkedList::stream)
                .collect(Collectors.toList());
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
        Consumer prev = null;
        for (Consumer element: objects){
            if (element.getId() == consumer.getId()){
                prev = element;
                break;
            }
        }
        objects.remove(prev);
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
        Order prev = null;
        for (Order element: objects){
            if (element.getId() == order.getId()){
                prev = element;
                break;
            }
        }
        objects.remove(prev);
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
        Salepoint prev = null;
        for (Salepoint element: objects){
            if (element.getId() == salepoint.getId()){
                prev = element;
                break;
            }
        }
        objects.remove(prev);
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
        Warehouse prev = null;
        for (Warehouse element: objects){
            if (element.getId() == warehouse.getId()){
                prev = element;
                break;
            }
        }
        objects.remove(prev);
        objects.add(warehouse);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteSalepoint(Salepoint salepoint) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("salepoints.json");
        Type listType = new TypeToken<List<Salepoint>>(){}.getType();
        List<Salepoint> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.removeIf(e -> e.getId() == salepoint.getId());
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAllCellsByStorageId(int storageId) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("cells.json");
        Type listType = new TypeToken<List<Cell>>(){}.getType();
        List<Cell> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Iterator<Cell> iterator = objects.iterator();
        while (iterator.hasNext()){
            Cell cell = iterator.next();
            if (cell.getStorageId() == storageId){
                iterator.remove();
            }
        }
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
        objects.removeIf(e -> e.getId() == warehouse.getId());
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
        List<Boss> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.removeIf(e -> e.getId() == boss.getId());
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEmployee(Employee employee) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("employees.json");
        Type listType = new TypeToken<List<Employee>>(){}.getType();
        List<Employee> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        objects.removeIf(e -> e.getId() == employee.getId());
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteAllEmployeesByStorageId(int storageId) throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File("employees.json");
        Type listType = new TypeToken<List<Employee>>(){}.getType();
        List<Employee> objects;
        if (file.exists()) {
            objects = gson.fromJson(new FileReader(file), listType);
        } else {
            objects = new ArrayList<>();
        }
        Iterator<Employee> iterator = objects.iterator();
        while (iterator.hasNext()){
            Employee employee = iterator.next();
            if (employee.getStorageId() == storageId){
                iterator.remove();
            }
        }
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
