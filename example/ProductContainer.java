package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductContainer {
    protected int id;
    protected List<Product> products;
    protected int numberOfProducts;
    protected int amount;

    public ProductContainer() {
        this.products = new ArrayList<>();
        this.amount = 0;
        this.numberOfProducts = 0;
        this.id = (int) (System.currentTimeMillis() % Integer.MAX_VALUE) + 1;
    }

    public ProductContainer(int id) {
        this.products = new ArrayList<>();
        this.amount = 0;
        this.numberOfProducts = 0;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getAmount() {
        return amount;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public static int getListOfProductAmount (List<Product> products) {
        int amount = 0;
        for (Product product: products){
            amount += product.getPriceForConsumer();
        }
        return amount;
    }

    public int getNumberOfProductById(int productId){
        int k = 0;
        for (Product product: products){
            if (product.getId() == productId){
                k++;
            }
        }
        return k;
    }
}
