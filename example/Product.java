package org.example;

import java.io.IOException;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private int priceForConsumer;
    private int priceForSalePoint;

    public Product() {
        this.id = 0;
        this.name = null;
        this.priceForConsumer = 0;
        this.priceForSalePoint = 0;
    }

    public Product(int id, String name, int priceForConsumer, int priceForSalePoint) {
        this.id = id;
        this.name = name;
        this.priceForConsumer = priceForConsumer;
        this.priceForSalePoint = priceForSalePoint;
    }

    public static Product create(int id, String name, int priceForConsumer, int priceForSalePoint) throws IOException {
        Product product = new Product(id, name, priceForConsumer, priceForSalePoint);
        JSONWriter.saveNewProduct(product);
        return product;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriceForConsumer() {
        return priceForConsumer;
    }

    public int getPriceForSalePoint() {
        return priceForSalePoint;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceForConsumer(int priceForConsumer) {
        this.priceForConsumer = priceForConsumer;
    }

    public void setPriceForSalePoint(int priceForSalePoint) {
        this.priceForSalePoint = priceForSalePoint;
    }
}
