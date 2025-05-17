package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProductContainer {
    protected int id;
    protected List<Product> products;       //потом заменить на лист id продуктов
    protected int numberOfProducts;
    protected int amount;
    protected static int globalId = 1;

    public ProductContainer(List<Product> products) {
        this.products = products;
        this.amount = getListOfProductAmount(products);
        this.numberOfProducts = products.size();
        this.id = globalId++;
    }

    public ProductContainer() {
        this.products = new ArrayList<>();
        this.amount = 0;
        this.numberOfProducts = 0;
        this.id = globalId++;
    }

    public ProductContainer(int id) {
        this.products = new ArrayList<>();
        this.amount = 0;
        this.numberOfProducts = 0;
        this.id = id;
        globalId++;
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

    public static int getGlobalId() {
        return globalId;
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

    public int getProductContainerAmount () {
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
