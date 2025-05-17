package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Consumer extends User{
    private int id;
    private String fullname;
    private int basketId;
    private List<Integer> orderIds;
    private String city;
    private static int globalId = 1;

    public Consumer(String fullname, String city) {
        this.fullname = fullname;
        this.city = city;
        this.basketId = globalId;
        this.id = globalId++;
        this.orderIds = new ArrayList<>();
    }

    public static Consumer create(String fullname, String city) throws FileNotFoundException {
        Consumer consumer = new Consumer(fullname, city);
        JSONWriter.saveNewConsumer(consumer);
        Basket.create(consumer.getBasketId());
        return consumer;
    }

    public static int getGlobalId() {
        return globalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private Salepoint getWorkingSalepoint() throws IOException {
        List<Salepoint> list = JSONReader.readSalepoints();
        for (Salepoint salepoint: list){
            if (salepoint.getCity().equals(city)){
                return salepoint;
            }
        }
        return null;
    }

    public void makeOrder() throws IOException {
        Basket basket = JSONReader.getBasketById(basketId);
        List<Product> basketList = basket.getProducts();
        Order newOrder = new Order(basketList);
        orderIds.add(newOrder.getId());
        Salepoint salepoint = getWorkingSalepoint();
        salepoint.increaseRevenue(newOrder.getAmount());
        for (Product product: basketList){
            salepoint.reduceProductById(product.getId());
            basket.deleteProduct(product.getId());
        }
    }

    public void showOrders() throws IOException {
        List<Order> orders = JSONReader.readOrders();
        if (orderIds.size() > 0){
            for (int i = 1; i < orderIds.size(); i++){
                for (Order order: orders){
                    if (order.getId() == i){
                        order.printOrder();
                    }
                }
            }
        }
    }

    public int numberOfOrderedProductsById(int productId) throws IOException {
        List<Order> list = JSONReader.readOrders();
        int numberOfProducts = 0;
        for (Order order: list){
            numberOfProducts += order.getNumberOfProductById(productId);
        }
        return numberOfProducts;
    }

    public void returnProductById(int productId) throws IOException {
        //ищем пустую ячейку на пункте продаж
        Salepoint salepoint = getWorkingSalepoint();
        Cell cell = Cell.getEmptyCellByStorageId(salepoint.getId());
        cell.setProductId(productId);
        salepoint.increaseRevenue((-1) * JSONReader.getProductById(productId).getPriceForConsumer());
    }
}
