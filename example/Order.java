package org.example;

import java.io.IOException;
import java.util.List;

public class Order extends ProductContainer{
    private static int currentId;

    static {
        try {
            currentId = JSONReader.getNumberOfOrders();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Order(List<Product> products) {
        this.products = products;
        this.amount = getListOfProductAmount(products);
        this.numberOfProducts = products.size();
        this.id = currentId+1;
    }

    public static Order create (List<Product> products) throws IOException {
        Order order = new Order(products);
        JSONWriter.saveNewOrder(order);
        currentId++;
        return order;
    }

    public Order() {
        super();
    }

    public void printOrder(){
        System.out.println("==============================================");
        System.out.println("☆☆☆☆☆☆☆☆☆ id заказа: " + id + " ☆☆☆☆☆☆☆☆☆");
        System.out.printf("%-40s | %16s | %16s | %10s%n", "Товар", "id", "Цена", "Количество");
        for (Product product: products){
            System.out.printf(
                    "%-40s | %16d | %16d | %10s%n",
                    "☆ " + product.getName(),
                    product.getId(),
                    product.getPriceForConsumer(),
                    getNumberOfProductById(product.getId()));
        }
    }
}
