package org.example;

import java.util.Scanner;

public class InterfaceController {
    protected boolean shouldExit;

    public InterfaceController() {
        this.shouldExit = false;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    public void goBackOrClose() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Хотите продолжить или закрыть приложение?");
        System.out.println("===========================================");
        System.out.println("☆ Продолжить - 1\n" +
                "☆ Закрыть приложение - 2");
        String choice = sc.nextLine().trim();
        if (choice.isEmpty()) {
            choice = sc.nextLine();
        }
        switch (choice){
            case "1":
                break;
            case "2":
                setShouldExit(true);
                break;
        }
    }
}
