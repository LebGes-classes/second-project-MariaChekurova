package org.example;

import java.io.FileNotFoundException;

public class Salepoint extends Storage{
    private int profit;

    public Salepoint(String city, int capasity) {
        super(city, capasity);
    }

    public Salepoint(int id, String city, int capasity) {
        super(id, city, capasity);
    }

    public Salepoint(int id, String city, int capasity, int fullCellsNumber) {
        super(id, city, capasity, fullCellsNumber);
    }

    public static Salepoint create(String city, int capasity) throws FileNotFoundException {
        Salepoint salepoint = new Salepoint(city, capasity);
        createNewEmptyCells(capasity);
        JSONWriter.saveNewSalepoint(salepoint);
        return salepoint;
    }

    public static Salepoint create(int id, String city, int capasity) throws FileNotFoundException {
        Salepoint salepoint = new Salepoint(id, city, capasity);
        createNewEmptyCells(capasity);
        JSONWriter.saveNewSalepoint(salepoint);
        return salepoint;
    }

    public static Salepoint create(int id, String city, int capasity, int fullCellsNumber) throws FileNotFoundException {
        Salepoint salepoint = new Salepoint(id, city, capasity, fullCellsNumber);
        JSONWriter.saveNewSalepoint(salepoint);
        return salepoint;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void returnProduct(){
        // дописать
    }
}
