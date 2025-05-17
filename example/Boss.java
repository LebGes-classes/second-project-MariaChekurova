package org.example;

import java.io.IOException;

public class Boss extends User implements Worker {
    protected int id;
    protected String fullName;
    protected int storageId;
    protected static int globalId = 1;

    public Boss(String fullName, int storageId) {
        this.fullName = fullName;
        this.storageId = storageId;
        this.id = globalId++;
    }

    public static Boss create(String fullName, int storageId) throws IOException {
        Boss boss = new Boss(fullName, storageId);
        JSONWriter.saveNewBoss(boss);
        return boss;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getStorageId() {
        return storageId;
    }

    public static int getGlobalId() {
        return globalId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }
}
