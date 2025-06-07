package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Boss {
    protected int id;
    protected String fullName;
    protected int storageId;

    public Boss() {
        this.fullName = null;
        this.storageId = 0;
        this.id = (int) (System.currentTimeMillis() % Integer.MAX_VALUE) + 1;
    }

    public Boss(int id, String fullName, int storageId) {
        this.fullName = fullName;
        this.storageId = storageId;
        this.id = id;
    }

    public Boss(String fullName, int storageId) {
        this.fullName = fullName;
        this.storageId = storageId;
        this.id = (int) (System.currentTimeMillis() % Integer.MAX_VALUE) + 1;
    }

    public static Boss create(String fullName, int storageId) throws IOException {
        Boss boss = new Boss(fullName, storageId);
        JSONWriter.saveNewBoss(boss);
        return boss;
    }

    public static Boss create(int id, String fullName, int storageId) throws IOException {
        Boss boss = new Boss(id, fullName, storageId);
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

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    public void openNewSalepoint() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите город, в котором хотите открыть пункт продаж (Enter, если хотите отменить действие)");
        String city = sc.nextLine();
        if (!(city.isEmpty())) {
            System.out.println("Введите вместимость нового пункта продаж (количество ячеек):");
            int capacity = sc.nextInt();
            sc.nextLine();
            Salepoint newSalepoint = Salepoint.create(city, capacity);
            System.out.println("Ведите ФИО руководителя открытого пункта продаж");
            String name = sc.nextLine();
            Boss.create(name, newSalepoint.getId());
            System.out.println("☆☆☆☆☆ Новый пункт продаж в городе " + city +  " открыт ☆☆☆☆☆");
        }
    }

    public void openNewWarehouse() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите город, в котором хотите открыть склад (Enter, если хотите отменить действие)");
        String city = sc.nextLine();
        if (!(city.isEmpty())) {
            System.out.println("Введите вместимость нового склада (количество ячеек):");
            int capacity = sc.nextInt();
            sc.nextLine();
            Warehouse newWarehouse = Warehouse.create(city, capacity);
            System.out.println("Ведите ФИО руководителя открытого склада");
            String name = sc.nextLine();
            Boss.create(name, newWarehouse.getId());
            System.out.println("☆☆☆☆☆ Новый склад в городе " + city +  " открыт ☆☆☆☆☆");
        }
    }
}
