package service.impl;

import dao.ProductDao;
import dao.UsersDao;
import enums.Companies;
import excp.MyException;
import model.Products;
import service.ProductService;

import java.time.LocalDate;
import java.util.Scanner;

import static enums.Enums.SALE;

public class ProductServiceImpl implements ProductService {

    //Доавляем уже созданный продукт
    @Override
    public void addProduct(Products products) {
        // надо добавить проверку на срок годности if(DueToDate - DateOfManufacturing > 30 days)
        boolean exist = true;
        try {
            Companies.valueOf(products.getCompany());
        } catch (IllegalArgumentException e) {
            exist = false;
        }
        if (exist) ProductDao.addProductToDB(products);
        System.out.println("Продукции компании " + products.getCompany() + "не допускаюся!");
    }

    //Получаем продукт по имени
    @Override
    public void getProductByName(String name) throws MyException{
        boolean checkForCorrect = false;
        for (Products products: ProductDao.getDBProducts()) {
            try {
                if (products.getName().equals(name)) {
                    System.out.println(products);
                    checkForCorrect = true;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (!checkForCorrect) throw new MyException("Name doesn't exist! -> ");
    }

    @Override
    public void checkForDueToProduct() {
        LocalDate dateNow = LocalDate.now();
        // 2022-12-12 время
        //2022-12-10 время до
        //Выводит все пролукты с просроченным сроком даты
        System.out.println("---- Все просроченные продукты ниже! ----");
        for (int i = 0; i < ProductDao.getDBProducts().size(); i++) {
            LocalDate check = ProductDao.getDBProducts().get(i).getDueToDate();
            //Логика костыль Переделать в будущем!
            if (check.getYear() < dateNow.getYear()) {
                //Если истек год
                System.out.println(ProductDao.getDBProducts().get(i));
            } else if(check.getYear() == dateNow.getYear() && check.getMonthValue() < dateNow.getMonthValue()) {
                //если год равен но истек месяц
                System.out.println(ProductDao.getDBProducts().get(i));
            } else if (check.getYear() == dateNow.getYear()
                    && check.getMonthValue() == dateNow.getMonthValue()
                    && check.getDayOfMonth() < dateNow.getDayOfMonth()) {
                // если год и месяц раеен но дни истекли
                System.out.println(ProductDao.getDBProducts().get(i));
            }
        }
    }

    @Override
    public void checkForDueToWeekProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- Введите Логин! ----");
        String log = scanner.nextLine();
        System.out.println("---- Введите Пароль! ----");
        String pas = scanner.nextLine();
        //Проверяем на допуск к проверке на СКИДКИ
        if (UsersDao.checkUser(log, pas)) {
            LocalDate dateNow = LocalDate.now();
            for (int i = 0; i < ProductDao.getDBProducts().size(); i++) {
                LocalDate check = ProductDao.getDBProducts().get(i).getDueToDate();
                //Логика костыль Переделать в будущем!
                if (check.getYear() == dateNow.getYear()
                        && check.getMonthValue() == dateNow.getMonthValue()
                        && check.getDayOfMonth() > dateNow.getDayOfMonth()
                        && (check.getDayOfMonth() - dateNow.getDayOfMonth()) <= 7) {
                    // если год и месяц равны дней до проскрочки есть но их равно или меньше 7
                    ProductDao.getDBProducts().get(i).setEnu(SALE);
                    System.out.println(ProductDao.getDBProducts().get(i));
                }
                else if (ProductDao.getDBProducts().size() - 1 == i){
                    System.out.println("---- Скидочных продуктов нет! ----");
                }
            }
        }
    }

    // Получаем все продукты из базы данных
    @Override
    public void getAllProduct() throws MyException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("---- Введите Логин! ----");
        String log = scanner.nextLine();
        System.out.println("---- Введите Пароль! ----");
        String pas = scanner.nextLine();
        //Дальше идет логика и проверка на допуск к продутам
        if (UsersDao.checkUser(log, pas)) {
            for (Products products : ProductDao.getDBProducts()) {
                System.out.println(products);
            }
        }
        else {
            throw new MyException("---- Такой пользователь отсутсвует!  ----");
        }

    }

}
