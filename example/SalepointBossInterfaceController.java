package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SalepointBossInterfaceController extends BossInterfaceController{
    private Salepoint workingSalepoint;

    public SalepointBossInterfaceController(Boss workingBoss) throws IOException {
        super(workingBoss);
        this.workingSalepoint = (Salepoint) workingStorage;
    }

    public void showOptions() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (!shouldExit) {
            System.out.println("Выберите опцию: ");
            System.out.println("===========================================");
            System.out.println("☆ Показать информацию о пункте продаж - 1\n" +
                    "☆ Показать список сотрудников - 2\n" +
                    "☆ Нанять нового сотрудника - 3\n" +
                    "☆ Уволить сотрудника - 4\n" +
                    "☆ Закупить товар - 5\n" +
                    "☆ Закрыть пункт продаж - 6\n" +
                    "☆ Сменить босса - 7\n" +
                    "☆ Открыть новый пункт продаж - 8\n" +
                    "☆ Выход - 9\n");
            String choice = sc.nextLine().trim();
            if (choice.isEmpty()) {
                choice = sc.nextLine();
            }
            switch (choice) {
                case "1":
                    workingSalepoint.printInformation();
                    break;
                case "2":
                    workingSalepoint.printEmployees();
                    break;
                case "3":
                    askToHire();
                    break;
                case "4":
                    askToDismiss();
                    break;
                case "5":
                    askToPurchase();
                    break;
                case "6":
                    askToCloseSalepoint();
                    break;
                case "7":
                    askToChangeBoss();
                    break;
                case "8":
                    workingBoss.openNewSalepoint();
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
                    break;
            }
        }
    }

    private void askToPurchase() throws IOException {
        List<Warehouse> warehouses = JSONReader.readWarehouses();
        Scanner sc = new Scanner(System.in);
        System.out.println("☆☆☆ Товары в наличии на пункте продаж:");
        workingSalepoint.printProducts();
        System.out.println("☆☆☆ Товары в наличии на складах:");
        for (Warehouse warehouse: warehouses){
            System.out.println("☆☆☆ id склада: " + warehouse.getId() + " ☆☆☆");
            warehouse.printProducts();
        }
        System.out.println("Введите id склада, с которого хотите заказать товар");
        int warehouseId = sc.nextInt();
        sc.nextLine();
        Warehouse warehouse = JSONReader.getWarehouseById(warehouseId);
        System.out.println("Введите в строку через пробел id товара, который хотите закупить, и его количество (enter для завершения ввода)");
        String enter = sc.nextLine();
        if (!(enter.isEmpty())){
            enter = enter.replaceAll("\\s+", " ");
            int productId = Integer.parseInt(enter.split(" ")[0]);
            int quantity = Integer.parseInt(enter.split(" ")[1]);
            if (warehouse.getAllProductNumberByStorageId().get(productId) >= quantity){
                workingSalepoint.purchase(productId, quantity, warehouse);
                System.out.println("Вы успешно закупили товар!");
            } else {
                System.out.println("Товара с введенным id нет на складе в указанном количестве, попробуйте еще");
                askToPurchase();
            }
        }
        goBackOrClose();
    }

    private void askToCloseSalepoint() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Вы уверены, что хотите закрыть пункт продаж?");
        System.out.println("☆ Да - 1\n" +
                "☆ Нет, вернуться назад - 2");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                workingSalepoint.close();
                shouldExit = true;
                break;
            default:
                break;
        }
    }
}
