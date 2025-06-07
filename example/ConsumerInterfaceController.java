package org.example;

import java.io.IOException;
import java.util.*;

public class ConsumerInterfaceController extends InterfaceController{
    private Consumer workingConsumer;
    private Salepoint workingSalepoint;
    private Basket userBasket;

    public ConsumerInterfaceController(Consumer workingConsumer) throws IOException {
        super();
        this.workingConsumer = workingConsumer;
        this.workingSalepoint = JSONReader.getSalepointByCity(workingConsumer.getCity());
        this.userBasket = JSONReader.getBasketById(workingConsumer.getBasketId());
    }

    public void showOptions() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (!shouldExit) {
            System.out.println("Выберите опцию: ");
            System.out.println("=".repeat(52));
            System.out.println("☆ Показать товары, доступные для заказа - 1\n" +
                    "☆ Показать корзину - 2\n" +
                    "☆ Показать мои заказы - 3\n" +
                    "☆ Вернуть товар - 4\n" +
                    "☆ Выход - 5\n");
            String choice = sc.nextLine().trim();
            if (choice.isEmpty()) {
                choice = sc.nextLine();
            }
            switch (choice) {
                case "1":
                    workingSalepoint.printProducts();
                    askToAdd();
                    break;
                case "2":
                    userBasket.showBasket();
                    askToAddOrDeleteProducts();
                    break;
                case "3":
                    workingConsumer.showOrders();
                    System.out.println("Нажмите Enter чтобы вернуться в меню...");
                    sc.nextLine();
                    break;
                case "4":
                    askToReturnProducts();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова");
                    break;
            }
        }
    }

    public void askToAdd() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean isAddedProducts = false;
        System.out.println("Введите 0, если не хотите добавлять товары в корзину");
        System.out.println("=".repeat(52));
        System.out.println("Введите id товаров, которые хотите добавить в корзину (Enter для завершения ввода): ");
        List<Integer> numbersOfProducts = workingSalepoint.getAllProductNumberByStorageId();
        Integer[] variableNumberOfProducts = Arrays.copyOf(
                numbersOfProducts.toArray(),
                numbersOfProducts.toArray().length,
                Integer[].class
        );
        while (true) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            try {
                int productId = Integer.parseInt(input);
                int variableNumberOfProduct = variableNumberOfProducts[productId];
                if (productId != 0) {
                    if (JSONChecker.isProductInStorage(productId, workingSalepoint.getId())) {
                        if (variableNumberOfProduct > 0) {
                            userBasket.addProduct(productId);
                            isAddedProducts = true;
                            variableNumberOfProducts[productId] -= 1;
                        } else {
                            System.out.println("Невозможно добавить в корзину, товара больше нет в наличии");
                        }
                    } else {
                        System.out.println("Товара с таким id нет на пункте продаж");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите ЧИСЛО (id товара) или Enter для завершения");
            }
        }
        if (userBasket.getNumberOfProducts() > 0) {
            if (isAddedProducts){
                JSONWriter.saveModifiedBasket(userBasket); //сохранили измененную корзину
                askToOrder();
            } else {
                askToOrder();
            }
        } else {
            goBackOrClose();
        }
    }

    public void askToOrder() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("☆☆☆ Хотите заказать товары из корзины? ☆☆☆");
        System.out.println("=".repeat(52));
        System.out.println("☆ Заказать товары - 1\n" +
                "☆ Закрыть корзину - 2\n");
        String choice = sc.nextLine().trim();
        if (choice.isEmpty()) {
            choice = sc.nextLine();
        }
        switch (choice){
            case "1":
                workingConsumer.makeOrder();
                userBasket.setProducts(new ArrayList<>());
                break;
            case "2":
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова");
                askToOrder();
                break;
        }
        goBackOrClose();
    }

    private void askToAddOrDeleteProducts() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите опцию: ");
        System.out.println("=".repeat(52));
        System.out.println("☆ Добавить товары в корзину - 1\n" +
                "☆ Закрыть корзину - 2\n" +
                "☆ Удалить товары из корзины - 3\n");
        String choice = sc.nextLine().trim();
        if (choice.isEmpty()) {
            choice = sc.nextLine();
        }
        switch (choice){
            case "1":
                workingSalepoint.printProducts();
                askToAdd();
                break;
            case "2":
                goBackOrClose();
                break;
            case "3":
                askToDelete();
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова");
                askToAddOrDeleteProducts();
                break;
        }
    }

    private void askToDelete() throws IOException {
        Scanner sc = new Scanner(System.in);
        userBasket.showBasket();
        System.out.println("Введите id товаров, которые вы хотите убрать из корзины (Enter для завершения ввода):");
        while (true) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            try {
                int productId = Integer.parseInt(input);
                if (productId != 0) {
                    if (JSONChecker.isProductInStorage(productId, workingSalepoint.getId())) {
                        if (JSONChecker.isProductInBasket(productId, userBasket.getId())){
                            userBasket.deleteProduct(productId);
                        } else {
                            System.out.println("Товара с таким id нет в вашей корзине, попробуйте снова");
                        }
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число (id товара) или Enter для завершения");
            }
        }
        JSONWriter.saveModifiedBasket(userBasket);
        goBackOrClose();
    }

    private void askToReturnProducts() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите id товаров, которые вы хотите вернуть (Enter для завершения ввода):");
        List<Integer> numbersOfOrderedProducts = new ArrayList<>(Collections.nCopies(100, 0));
        List<Boolean> isPrinted = new ArrayList<>(Collections.nCopies(100, false));
        for (int i = 1; i < numbersOfOrderedProducts.size(); i++){
            numbersOfOrderedProducts.set(i, workingConsumer.numberOfOrderedProductsById(i));
        }
        while (true) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            try {
                int productId = Integer.parseInt(input);
                int numberOfOrderedProducts = numbersOfOrderedProducts.get(productId);
                if (numberOfOrderedProducts > 0){ //проверка сколько товаров с таким id заказал покупатель в классе Consumer
                    workingConsumer.returnProductById(productId);
                    numbersOfOrderedProducts.set(productId, numberOfOrderedProducts-1);
                    isPrinted.set(productId, true);
                } else if (isPrinted.get(productId)){
                    System.out.println("Вы больше не можете вернуть этот товар, так как вернули все заказанные товары с этим id");
                } else {
                    System.out.println("Вы не заказывали товаров с таким id, попробуйте снова");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число (id товара) или Enter для завершения");
            }
        }
        goBackOrClose();
    }
}
