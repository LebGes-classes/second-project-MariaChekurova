package org.example;

import java.io.IOException;
import java.util.List;

public class Employee implements Worker {
    protected int id;
    protected String fullName;
    protected int storageId;
    protected static int globalId = 1;

    public Employee(String fullName, int storageId) {
        this.fullName = fullName;
        this.storageId = storageId;
        this.id = globalId++;
    }

    public static Employee create (String fullName, int storageId) throws IOException {
        Employee employee = new Employee(fullName, storageId);
        JSONWriter.saveNewEmployee(employee);
        return employee;
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
