package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Consumer {
    private static int currentId;

    static {
        try {
            currentId = JSONReader.getNumberOfConsumers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int id;
    private String fullname;
    private int basketId;
    private List<Integer> orderIds;
    private String city;

    public Consumer(String fullname, String city) {
        this.fullname = fullname;
        this.city = city;
        this.id = currentId+1;
        this.basketId = id;
        this.orderIds = new ArrayList<>();
    }

    public Consumer() {
        this.fullname = null;
        this.city = null;
        this.id = currentId+1;
        this.basketId = id;
        this.orderIds = new ArrayList<>();
    }

    public static Consumer create(String fullname, String city) throws FileNotFoundException {
        Consumer consumer = new Consumer(fullname, city);
        JSONWriter.saveNewConsumer(consumer);
        Basket.create(consumer.getBasketId());
        currentId++;
        return consumer;
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
        Order newOrder = Order.create(basketList);
        orderIds.add(newOrder.getId());
        Salepoint salepoint = getWorkingSalepoint();
        salepoint.increaseRevenue(newOrder.getAmount());
        salepoint.increaseProfit(newOrder.getAmount());
        Iterator<Product> iterator = basketList.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            salepoint.reduceProductById(product.getId());
            iterator.remove();
        }
        basket.setProducts(basketList);
        basket.setAmount(0);
        basket.setNumberOfProducts(0);
        JSONWriter.saveModifiedBasket(basket);
        JSONWriter.saveModifiedSalepoint(salepoint);
        JSONWriter.saveModifiedConsumer(this);
        System.out.println("☆☆☆☆☆ Спасибо за заказ! ☆☆☆☆☆");
    }

    public void showOrders() throws IOException {
        Path filePath = Paths.get("orders.json");
        if (Files.exists(filePath)){
            List<Order> orders = JSONReader.readOrders();
            if (JSONChecker.ifConsumerMadeOrders(this)){
                for (int i = 0; i < orderIds.size(); i++){
                    for (Order order: orders){
                        if (order.getId() == orderIds.get(i)){
                            order.printOrder();
                        }
                    }
                }
            } else {
                System.out.println("Вы ещё не сделали ни одного заказа");
            }
        } else {
            System.out.println("Вы ещё не сделали ни одного заказа=======");
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
        //List<Integer> orderedProducts = getAllOrderedProductsNumbers();

        Cell cell = salepoint.getEmptyCell();
        cell.setProductId(productId);
        salepoint.increaseRevenue((-1) * JSONReader.getProductById(productId).getPriceForConsumer());
        salepoint.setEmptyCellsNumber(salepoint.emptyCellsNumber-1);
        salepoint.setFullCellsNumber(salepoint.fullCellsNumber+1);
        JSONWriter.saveModifiedSalepoint(salepoint);
        JSONWriter.saveModifiedCell(cell);
    }

    private List getAllOrderedProductsNumbers() throws IOException {        //считаем количество единиц заказанного товара с каждым id
        List<Integer> numbers = new ArrayList<>(Collections.nCopies(15, 0));
        Iterator<Integer> iterator = orderIds.iterator();
        while (iterator.hasNext()){
            Order order = JSONReader.getOrderById(iterator.next());
            for (Product product: order.getProducts()){
                int productId = product.getId();
                numbers.set(productId, numbers.get(productId) + 1);
            }
        }
        return numbers;
    }
}
