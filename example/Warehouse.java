package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Warehouse extends Storage{

    public Warehouse() {
        super();
    }

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
        createNewEmptyCells(capasity, warehouse.getId());
        JSONWriter.saveNewWarehouse(warehouse);
        currentId++;
        return warehouse;
    }

    public static Warehouse create (int id, String city, int capasity) throws FileNotFoundException {
        Warehouse warehouse = new Warehouse(id, city, capasity);
        createNewEmptyCells(capasity, warehouse.getId());
        JSONWriter.saveNewWarehouse(warehouse);
        currentId++;
        return warehouse;
    }

    public static Warehouse create (int id, String city, int capasity, int fullCellsNumber) throws FileNotFoundException {
        Warehouse warehouse = new Warehouse(id, city, capasity, fullCellsNumber);
        JSONWriter.saveNewWarehouse(warehouse);
        currentId++;
        return warehouse;
    }

    public void addProduct(int productId) throws IOException {
        Cell foundCell = getEmptyCell();
        if (foundCell != null){
            foundCell.addProductToCell(productId);
            setEmptyCellsNumber(getEmptyCellsNumber() - 1);
            setFullCellsNumber(getFullCellsNumber() + 1);
            JSONWriter.saveModifiedWarehouse(this);
        }
    }

    public void close() throws IOException {
        super.close();
        JSONWriter.deleteWarehouse(this);
    }
}
