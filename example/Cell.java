package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Cell {
    private int id;
    private boolean isEmpty;
    private int productId;
    private int storageId;
    private static int globalId = 1;

    public Cell(int productId) {
        this.id = globalId++;
        this.isEmpty = false;
        this.productId = productId;
    }

    public Cell(int id, int productId, int storageId) {
        this.id = id;
        this.isEmpty = false;
        this.productId = productId;
        this.storageId = storageId;
    }

    public Cell() {
        this.id = globalId++;
        this.isEmpty = true;
        this.productId = 0;
        this.storageId = 0;
    }
    /// методы которые создают нровый экземпляр класа и добавляют его в json
    public static Cell create (int productId) throws FileNotFoundException {
        Cell cell = new Cell(productId);
        JSONWriter.saveNewCell(cell);
        return cell;
    }

    public static Cell create (int id, int productId, int storageId) throws FileNotFoundException {
        Cell cell = new Cell(id, productId, storageId);
        JSONWriter.saveNewCell(cell);
        return cell;
    }

    public static Cell create () throws FileNotFoundException {
        Cell cell = new Cell();
        JSONWriter.saveNewCell(cell);
        return cell;
    }

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean geisEmpty() {
        return isEmpty;
    }

    public int getProductId() {
        return productId;
    }

    public static int getGlobalId() {
        return globalId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public void setProductId(int productId) {
        this.productId = productId;
        this.isEmpty = false;
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public static Cell getEmptyCellByStorageId(int storageId) throws IOException {
        List<Cell> cells = JSONReader.readCells();
        for (Cell cell: cells){
            if (cell.getStorageId() == storageId && cell.isEmpty){
                return cell;
            }
        }
        return null;
    }
}
