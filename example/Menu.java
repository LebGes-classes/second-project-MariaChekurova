package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public void showMenu() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("==========MENU==========");
        System.out.println("Ведите ваш тип пользователя");
        System.out.println("1 - Покупатель\n" +
                "2 - Начальник склада\n" +
                "3 - Начальник пункта продаж\n");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                showConsumerInterface();
                break;
            case "2":
                showWarehouseBossInterface();
                break;
            case "3":
                showSalepointBossInterface();
                break;
            default:
                System.out.println("Ошибка ввода, попробуйте снова");
                showMenu();
                break;
        }
    }

    private void showConsumerInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ФИО:");
        String fullname = sc.nextLine();
        Consumer workingConsumer = null;
        if (JSONChecker.isInConsumers(fullname)) {
            workingConsumer = JSONReader.getConsumerByFullname(fullname);
        } else {
            System.out.println("Добро пожаловать в магазин прикольных вещей!\n" +
                    "Введите свой город:");
            String city = sc.nextLine();
            if (JSONChecker.isValidCity(city)){
                workingConsumer = new Consumer(fullname, city);
            } else {
                System.out.println("К сожалению, в этом городе пока не открыли пункт продаж");
            }
        }
        ConsumerInterfaceController controller = new ConsumerInterfaceController(workingConsumer);
        controller.showOptions();
    }

    private void showWarehouseBossInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ФИО:");
        String fullname = sc.nextLine();
        Boss workingBoss = null;
        if (JSONChecker.isInBosses(fullname)) {
            workingBoss = JSONReader.getBossByFullname(fullname);
        } else {
            throw new RuntimeException("Начальника склада с таким ФИО нет");
        }
        WarehouseBossInterfaceController controller = new WarehouseBossInterfaceController(workingBoss);
        controller.showOptions();
    }

    private void showSalepointBossInterface() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ФИО:");
        String fullname = sc.nextLine();
        Boss workingBoss = null;
        if (JSONChecker.isInBosses(fullname)) {
            workingBoss = JSONReader.getBossByFullname(fullname);
        } else {
            throw new RuntimeException("Начальника пункта продаж с таким ФИО нет");
        }
        SalepointBossInterfaceController controller = new SalepointBossInterfaceController(workingBoss);
        controller.showOptions();
    }
}
