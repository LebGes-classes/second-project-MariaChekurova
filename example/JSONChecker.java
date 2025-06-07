package org.example;

import java.io.IOException;
import java.util.List;

public class JSONChecker {
    public static boolean isInConsumers(String fullname) throws IOException {
        List<Consumer> consumerList = JSONReader.readConsumers();
        for (Consumer consumer: consumerList){
            if (consumer.getFullname().equals(fullname)){
                return true;
            }
        }
        return false;
    }

    public static boolean isInBosses(String fullname) throws IOException {
        List<Boss> bossList = JSONReader.readBosses();
        for (Boss boss: bossList){
            if (boss.getFullName().equals(fullname)){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidCity(String city) throws IOException {
        List<Storage> storageList = JSONReader.readWarehouses();
        storageList.addAll(JSONReader.readSalepoints());
        for (Storage storage: storageList){
            if (storage.getCity().equals(city)){
                return true;
            }
        }
        return false;
    }

    public static boolean isProductInStorage (int productId, int storageId) throws IOException {
        List<Cell> cellList = JSONReader.readCells();
        for (Cell cell: cellList){
            if (cell.getProductId() == productId && cell.getStorageId() == storageId){
                return true;
            }
        }
        return false;
    }

    public static boolean isProductInBasket (int productId, int basketId) throws IOException {
        List<Product> products = JSONReader.getBasketById(basketId).getProducts();
        for (Product product: products){
            if (product.getId() == productId){
                return true;
            }
        }
        return false;
    }

    public static boolean ifConsumerMadeOrders (Consumer consumer) throws IOException {
        List<Order> orderList = JSONReader.readOrders();
        for (Order order: orderList){
            if (consumer.getOrderIds().contains(order.getId())){
                return true;
            }
        }
        return false;
    }
}
