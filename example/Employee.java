package org.example;

import java.io.IOException;

public class Employee {
    private static int currentId;

    static {
        try {
            currentId = JSONReader.getNumberOfEmployees();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected int id;
    protected String fullName;
    protected int storageId;
    protected int salary;

    public Employee() {
        this.fullName = null;
        this.storageId = 0;
        this.id = currentId + 1;
        this.salary = 0;
    }

    public Employee(String fullName, int storageId, int salary) {
        this.fullName = fullName;
        this.storageId = storageId;
        this.id = currentId + 1;
        this.salary = salary;
    }

    public static Employee create (String fullName, int storageId, int salary) throws IOException {
        Employee employee = new Employee(fullName, storageId, salary);
        currentId++;
        JSONWriter.saveNewEmployee(employee);
        return employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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
