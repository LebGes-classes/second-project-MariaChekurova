package org.example;

import java.io.IOException;
import java.util.List;

public class Order extends ProductContainer{

    public Order(List<Product> products) {
        super(products);
    }

    public static Order create (List<Product> products) throws IOException {
        Order order = new Order(products);
        JSONWriter.saveNewOrder(order);
        return order;
    }

    public Order() {

    }

    public void printOrder(){
        System.out.println("==============================================");
        System.out.println("☆☆☆☆☆☆☆☆☆ id заказа: " + id + " ☆☆☆☆☆☆☆☆☆");
        System.out.printf("%-32s | %16d | %16d | %10.2f%n", "Товар", "id", "Цена", "Количество");
        for (Product product: products){
            System.out.printf(
                    "%-32s | %16d | %16d | %10.2f%n",
                    "☆ " + product.getName(),
                    product.getId(),
                    product.getPriceForConsumer(),
                    getNumberOfProductById(product.getId()));
        }
    }
}
