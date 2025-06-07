package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Cell {
    private static int currentId;

    static {
        try {
            currentId = JSONReader.getNumberOfCells();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int id;
    @JsonProperty("isEmpty")
    private boolean isEmpty;
    private int productId;
    private int storageId;


    public Cell(@JsonProperty("id") int id,
                @JsonProperty("productId") int productId,
                @JsonProperty("storageId") int storageId) {
        this.id = id;
        this.productId = productId;
        if (productId != 0){
            this.isEmpty = false;
        } else {
            this.isEmpty = true;
        }
        this.storageId = storageId;
    }

    @JsonCreator
    public Cell(@JsonProperty("id") int id,
                @JsonProperty("productId") int productId,
                @JsonProperty("storageId") int storageId,
                @JsonProperty("isEmpty") boolean isEmpty) {
        this.id = id;
        this.productId = productId;
        this.isEmpty = isEmpty;
        this.storageId = storageId;
    }

    public Cell() {
        this.id = currentId+1;
        this.isEmpty = true;
        this.productId = 0;
        this.storageId = 0;
       // currentId++;
    }

    public Cell(int storageId) {
        this.id = currentId+1;
        this.isEmpty = true;
        this.productId = 0;
        this.storageId = storageId;
        currentId++;
    }

    /// методы которые создают нровый экземпляр класа и добавляют его в json

    /**public static Cell create (int id, int productId, int storageId) throws FileNotFoundException {
        Cell cell = new Cell(id, productId, storageId);
        JSONWriter.saveNewCell(cell);
        currentId++;
        return cell;
    }

    public static Cell create () throws FileNotFoundException {
        Cell cell = new Cell();
        JSONWriter.saveNewCell(cell);
        currentId++;
        return cell;
    }

    public static Cell create (int storageId) throws FileNotFoundException {
        Cell cell = new Cell(storageId);
        JSONWriter.saveNewCell(cell);
        currentId++;
        return cell;
    }**/

    public int getId() {
        return id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getProductId() {
        return productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmpty(boolean empty) {
        this.isEmpty = empty;
    }

    public void setProductId(int productId) {
        this.productId = productId;
        if (productId != 0){
            this.isEmpty = false;
        }
        //когда добавляется товар в ячейку, у нее сохраняются изменения в json
    }

    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public void addProductToCell(int productId) throws IOException {
        setEmpty(false);
        setProductId(productId);
        JSONWriter.saveModifiedCell(this);
    }
}
