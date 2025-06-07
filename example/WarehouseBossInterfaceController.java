package org.example;

import java.io.IOException;
import java.util.Scanner;

public class WarehouseBossInterfaceController extends BossInterfaceController{
    private Warehouse workingWarehouse;

    public WarehouseBossInterfaceController(Boss workingBoss) throws IOException {
        super(workingBoss);
        this.workingWarehouse = (Warehouse) workingStorage;
    }

    public void showOptions() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (!shouldExit) {
            System.out.println("Выберите опцию: ");
            System.out.println("===========================================");
            System.out.println("☆ Показать информацию о складе - 1\n" +
                    "☆ Показать список сотрудников - 2\n" +
                    "☆ Нанять нового сотрудника - 3\n" +
                    "☆ Уволить сотрудника - 4\n" +
                    "☆ Закрыть склад - 5\n" +
                    "☆ Сменить босса - 6\n" +
                    "☆ Открыть новый склад - 7\n" +
                    "☆ Выход - 8\n");
            String choice = sc.nextLine().trim();
            if (choice.isEmpty()) {
                choice = sc.nextLine();
            }
            switch (choice) {
                case "1":
                    workingWarehouse.printInformation();
                    break;
                case "2":
                    workingWarehouse.printEmployees();
                    break;
                case "3":
                    askToHire();
                    break;
                case "4":
                    askToDismiss();
                    break;
                case "5":
                    askToCloseWarehouse();
                    break;
                case "6":
                    askToChangeBoss();
                    break;
                case "7":
                    workingBoss.openNewWarehouse();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
                    break;
            }
        }
    }

    private void askToCloseWarehouse() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Вы уверены, что хотите закрыть склад?");
        System.out.println("☆ Да - 1\n" +
                "☆ Нет, вернуться назад - 2");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                workingWarehouse.close();
                shouldExit = true;
                break;
            default:
                break;
        }
    }
}
