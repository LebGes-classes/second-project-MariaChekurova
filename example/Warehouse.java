package org.example;

import java.io.FileNotFoundException;

public class Warehouse extends Storage{

    public Warehouse(String city, int capasity) {
        super(city, capasity);
    }

    public Warehouse(int id, String city, int capasity) {
        super(id, city, capasity);
    }

    public Warehouse(int id, String city, int capasity, int fullCellsNumber) {
        super(id, city, capasity, fullCellsNumber);
    }

    public static Warehouse create (String city, int capasity) throws FileNotFoundException {
        Warehouse warehouse = new Warehouse(city, capasity);
        createNewEmptyCells(capasity);
        JSONWriter.saveNewWarehouse(warehouse);
        return warehouse;
    }

    public static Warehouse create (int id, String city, int capasity) throws FileNotFoundException {
        Warehouse warehouse = new Warehouse(id, city, capasity);
        createNewEmptyCells(capasity);
        JSONWriter.saveNewWarehouse(warehouse);
        return warehouse;
    }

    public static Warehouse create (int id, String city, int capasity, int fullCellsNumber) throws FileNotFoundException {
        Warehouse warehouse = new Warehouse(id, city, capasity, fullCellsNumber);
        JSONWriter.saveNewWarehouse(warehouse);
        return warehouse;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getCapasity() {
        return capacity;
    }

    public int getEmptyCellsNumber() {
        return emptyCellsNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCapasity(int capasity) {
        this.capacity = capasity;
    }

    public void setEmptyCellsNumber(int emptyCellsNumber) {
        this.emptyCellsNumber = emptyCellsNumber;
    }

    public void showProductsOfAllWarehouses(){

    }

    public void showProducts(){

    }

    public void showBoss(){

    }

    public void showEmpoyees(){

    }
}
