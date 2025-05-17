package org.example;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Product pr1 = Product.create(1, "USB-подогреватель для кружки", 1800, 602);
        Product pr2 = Product.create(2, "Светящиеся кубики льда", 800, 307);
        Product pr3 = Product.create(3, "Тостер с гравировкой", 2750, 1000);
        Product pr4 = Product.create(4, "Беспроводная зарядка-«камень»", 990, 330);
        Product pr5 = Product.create(5, "Набор для выращивания мини-кактусов", 760, 250);
        Product pr6 = Product.create(6, "Будильник-убегающий робот", 1500, 705);
        Product pr7 = Product.create(7, "Набор посуды с принтами мемов", 2850, 1150);
        Product pr8 = Product.create(8, "Шлёпанцы с подсветкой", 1630, 360);
        Product pr9 = Product.create(9, "Намордник в виде утиного клюва", 870, 270);
        Product pr10 = Product.create(10, "Формочка для яичницы в виде сердца", 450, 115);

        Warehouse w1 = new Warehouse(1, "Москва", 100, 100);
        Warehouse w2 = new Warehouse(2, "Екатеригбург", 100, 100);

        Boss bw1 = new Boss("Ковалёв Артём Игоревич", 1);
        Employee ew1 = new Employee("Мухин Данил Владимирович", 1);
        Employee ew2 = new Employee("Иванова Анна Сергеевна", 1);
        Employee ew3 = new Employee("Петров Максим Олегович", 1);
        Employee ew4 = new Employee("Соколова Екатерина Ильинична", 1);

        Boss bw2 = new Boss("Морозова Алина Викторовна", 2);
        Employee ew5 = new Employee("Кузнецов Артём Николаевич", 2);
        Employee ew6 = new Employee("Морозова Дарья Петровна", 2);
        Employee ew7 = new Employee("Григорьев Иван Дмитриевич", 2);
        Employee ew8 = new Employee("Белова Ольга Викторовна", 2);

        Salepoint s1 = new Salepoint("Москва", 3);
        Salepoint s2 = new Salepoint("Санкт-Петербург", 4);
        Salepoint s3 = new Salepoint("Казань", 5);
        Salepoint s4 = new Salepoint("Новосибирск", 6);
        Salepoint s5 = new Salepoint("Екатеринбург", 7);
        Salepoint s6 = new Salepoint("Волгоград", 8);
        Salepoint s7 = new Salepoint("Красноярск", 9);

        Boss bs1 = new Boss("Григорьев Павел Денисович", 3);
        Employee es1 = new Employee("Смирнов Алексей Владимирович", 3);
        Employee es2 = new Employee("Козлова Марина Игоревна", 3);

        Boss bs2 = new Boss("Соколова Валерия Олеговна", 4);
        Employee es3 = new Employee("Павлов Денис Сергеевич", 4);
        Employee es4 = new Employee("Федорова Анна Александровна", 4);

        Boss bs3 = new Boss("Титов Максим Александрович", 5);
        Employee es5 = new Employee("Николаев Артем Олегович", 5);
        Employee es6 = new Employee("Орлова Елизавета Дмитриевна", 5);

        Boss bs4 = new Boss("Беляева Анна Сергеевна", 6);
        Employee es7 = new Employee("Волков Максим Ильич", 6);
        Employee es8 = new Employee("Андреева Виктория Станиславовна", 6);

        Boss bs5 = new Boss("Кузьмин Дмитрий Станиславович", 7);
        Employee es9 = new Employee("Титов Кирилл Алексеевич", 7);
        Employee es10 = new Employee("Семенова Алина Павловна", 7);

        Boss bs6 = new Boss("Орлова Екатерина Ильинична", 8);
        Employee es11 = new Employee("Борисов Евгений Викторович", 8);
        Employee es12 = new Employee("Михайлова Дарья Артемовна", 8);

        Boss bs7 = new Boss("Фёдоров Иван Артёмович", 9);
        Employee es13 = new Employee("Крылов Илья Николаевич", 9);
        Employee es14 = new Employee("Зайцева Екатерина Сергеевна", 9);

        List<Cell> cells1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cells1.add(new Cell(i, pr1.getId(), 1));
        }
        for (int i = 10; i < 20; i++) {
            cells1.add(new Cell(i, pr2.getId(), 1));
        }
        for (int i = 20; i < 30; i++) {
            cells1.add(new Cell(i, pr3.getId(), 1));
        }
        for (int i = 30; i < 40; i++) {
            cells1.add(new Cell(i, pr4.getId(), 1));
        }
        for (int i = 40; i < 50; i++) {
            cells1.add(new Cell(i, pr5.getId(), 1));
        }
        for (int i = 50; i < 60; i++) {
            cells1.add(new Cell(i, pr6.getId(), 1));
        }

        for (int i = 60; i < 70; i++) {
            cells1.add(new Cell(i, pr7.getId(), 1));
        }
        for (int i = 70; i < 80; i++) {
            cells1.add(new Cell(i, pr8.getId(), 1));
        }

        for (int i = 80; i < 90; i++) {
            cells1.add(new Cell(i, pr9.getId(), 1));
        }
        for (int i = 90; i < 100; i++) {
            cells1.add(new Cell(i, pr10.getId(), 1));
        }


        for (int i = 0; i < 10; i++) {
            cells1.add(new Cell(i+100, pr1.getId(), 2));
        }
        for (int i = 10; i < 20; i++) {
            cells1.add(new Cell(i+100, pr2.getId(), 2));
        }
        for (int i = 20; i < 30; i++) {
            cells1.add(new Cell(i+100, pr3.getId(), 2));
        }
        for (int i = 30; i < 40; i++) {
            cells1.add(new Cell(i+100, pr4.getId(), 2));
        }
        for (int i = 40; i < 50; i++) {
            cells1.add(new Cell(i+100, pr5.getId(), 2));
        }
        for (int i = 50; i < 60; i++) {
            cells1.add(new Cell(i+100, pr6.getId(), 2));
        }

        for (int i = 60; i < 70; i++) {
            cells1.add(new Cell(i+100, pr7.getId(), 2));
        }
        for (int i = 70; i < 80; i++) {
            cells1.add(new Cell(i+100, pr8.getId(), 2));
        }

        for (int i = 80; i < 90; i++) {
            cells1.add(new Cell(i+100, pr9.getId(), 2));
        }
        for (int i = 90; i < 100; i++) {
            cells1.add(new Cell(i+100, pr10.getId(), 2));
        }

        List<Boss> bosses = new ArrayList<>();
        bosses.add(bw1);
        bosses.add(bw2);
        bosses.add(bs1);
        bosses.add(bs2);
        bosses.add(bs3);
        bosses.add(bs4);
        bosses.add(bs5);
        bosses.add(bs6);
        bosses.add(bs7);

        List<Employee> employees = new ArrayList<>();
        employees.add(ew1);
        employees.add(ew2);
        employees.add(ew3);
        employees.add(ew4);
        employees.add(ew5);
        employees.add(ew6);
        employees.add(ew7);
        employees.add(ew8);
        employees.add(es1);
        employees.add(es2);
        employees.add(es3);
        employees.add(es4);
        employees.add(es5);
        employees.add(es6);
        employees.add(es7);
        employees.add(es8);
        employees.add(es9);
        employees.add(es10);
        employees.add(es11);
        employees.add(es12);
        employees.add(es13);
        employees.add(es14);

        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(w1);
        warehouses.add(w2);

        List<Salepoint> salepoints = new ArrayList<>();
        salepoints.add(s1);
        salepoints.add(s2);
        salepoints.add(s3);
        salepoints.add(s4);
        salepoints.add(s5);
        salepoints.add(s6);
        salepoints.add(s7);

        /**List<Product> products = new ArrayList<>();
        products.add(pr1);
        products.add(pr2);
        products.add(pr3);
        products.add(pr4);
        products.add(pr5);
        products.add(pr6);
        products.add(pr7);
        products.add(pr8);
        products.add(pr9);
        products.add(pr10);

        try {
            Writer writer = new FileWriter("products.json");
            new Gson().toJson(products, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }**/

        try {
            Writer writer = new FileWriter("bosses.json");
            new Gson().toJson(bosses, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer = new FileWriter("employees.json");
            new Gson().toJson(employees, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer = new FileWriter("salepoints.json");
            new Gson().toJson(salepoints, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer = new FileWriter("warehouses.json");
            new Gson().toJson(warehouses, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Writer writer = new FileWriter("cells.json");
            new Gson().toJson(cells1, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}