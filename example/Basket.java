package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Basket extends ProductContainer{

    public Basket(List<Product> products) {
        super(products);
    }

    public Basket() {

    }

    public Basket(int id) {
        super(id);
    }

    public static Basket create(List<Product> products) throws FileNotFoundException {
        Basket basket = new Basket(products);
        JSONWriter.saveNewBasket(basket);
        return basket;
    }

    public static Basket create() throws FileNotFoundException {
        Basket basket = new Basket();
        JSONWriter.saveNewBasket(basket);
        return basket;
    }

    public static Basket create(int id) throws FileNotFoundException {
        Basket basket = new Basket(id);
        JSONWriter.saveNewBasket(basket);
        return basket;
    }

    public void addProduct(int productId) throws IOException {
        Product product = JSONReader.getProductById(productId);
        products.add(product);
        numberOfProducts++;
        amount += product.getPriceForConsumer();
    }

    public void showBasket() throws IOException {
        Basket basket = JSONReader.getBasketById(id);
        List<Product> productList = basket.getProducts();
        int id, number, price, amount = 0;
        String productName;

        if (products.size() > 0) {
            System.out.printf("%-32s | %16d | %16d | %10.2f%n", "Товар", "id", "Цена", "Количество");
            for (Product product: productList){
                productName = product.getName();
                id = product.getId();;
                number = countNumberOfProductById(id);
                price = product.getPriceForConsumer();
                amount += price;
                System.out.printf(
                        "%-32s | %16d | %16d | %10.2f%n",
                        "☆ " + productName,
                        id,
                        price,
                        number);
            }
            System.out.println("☆ Общая стоимость корзины: " + amount);
            System.out.println("============================");
        } else {
            System.out.println("В вашей корзине пока нет товаров");
            System.out.println("================================");
        }
    }

    private int countNumberOfProductById(int productId){
        int number = 0;
        for (Product product: products){
            if (product.getId() == productId){
                number++;
            }
        }
        return number;
    }

    public void deleteProduct(int productId){
        for (Product product: products){
            if (product.getId() == productId){
                products.remove(product);
                break;
            }
        }
    }

}
