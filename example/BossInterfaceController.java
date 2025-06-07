package org.example;

import java.io.IOException;
import java.util.Scanner;

public class BossInterfaceController extends InterfaceController {
    protected Boss workingBoss;
    protected Storage workingStorage;

    public BossInterfaceController(Boss workingBoss) throws IOException {
        super();
        this.workingBoss = workingBoss;
        this.workingStorage = JSONReader.getStorageById(workingBoss.getStorageId());
    }

    protected void askToChangeBoss() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Вы уверены, что хотите сменить начальника пункта продаж?");
        System.out.println("☆ Да - 1\n" +
                "☆ Нет, вернуться назад - 2");
        String choice = sc.nextLine();
        switch (choice){
            case "1":
                workingStorage.changeBoss();
                setShouldExit(true);
                break;
            default:
                break;
        }
    }

    protected void askToHire() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ФИО сотрудника, которого хотите нанять на работу:");
        String name = sc.nextLine();
        System.out.println("Ведите размер заработной платы нового сотрудника:");
        int salary = sc.nextInt();
        Employee.create(name, workingStorage.getId(), salary);
        System.out.println("Вы успешно наняли нового сотрудника!");
        goBackOrClose();
    }

    protected void askToDismiss() throws IOException {
        Scanner sc = new Scanner(System.in);
        workingStorage.printEmployees();
        System.out.println("Введите id сотрудника, которого хотите уволить:");
        int id = sc.nextInt();
        Employee employee = JSONReader.getEmployeeById(id);
        JSONWriter.deleteEmployee(employee);
        goBackOrClose();
    }
}
