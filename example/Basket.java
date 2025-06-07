package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Basket extends ProductContainer{
    public Basket() {
        super();
    }

    public Basket(int id) {
        super(id);
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
        int id, number, price, amount = 0;
        String productName;
        List<Boolean> isPrinted = new ArrayList<>(Collections.nCopies(15, false));
        if (products.size() > 0) {
            System.out.printf("%-40s | %16s | %16s | %10s%n", "Товар", "id", "Цена", "Количество");
            System.out.println("=".repeat(90));
            for (Product product: products){
                productName = product.getName();
                id = product.getId();
                if (!(isPrinted.get(id))){
                    number = countNumberOfProductById(id);
                    price = product.getPriceForConsumer();
                    amount += price;
                    System.out.printf(
                            "%-40s | %16d | %16d | %10d%n",
                            "☆ " + productName,
                            id,
                            price,
                            number);
                    isPrinted.set(id, true);
                }
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
        this.setNumberOfProducts(numberOfProducts-1);
    }

}
