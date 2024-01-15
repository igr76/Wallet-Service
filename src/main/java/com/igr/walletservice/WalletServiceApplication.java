package com.igr.walletservice;

import com.igr.walletservice.controller.AccountController;
import com.igr.walletservice.controller.UserController;
import com.igr.walletservice.entity.User;
import com.igr.walletservice.jdbc.Liquibase;
import java.util.Scanner;

public class WalletServiceApplication {
    public static void main(String[] args) {
        UserController userController = new UserController();
        AccountController accountController = new AccountController();
        Liquibase.LiquibaseStart();
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("Меню кредитного сервиса");
            System.out.println("-------------------\n");
            System.out.println("1. создать пользователя");
            System.out.println("2. обновить пользователя");
            System.out.println("3. удалить пользователя");
            System.out.println("4. получить состояние баланса");
            System.out.println("5. пополнение баланса");
            System.out.println("6. снятие средств");
            System.out.println("7. получить историю операций");
            System.out.println("8. Завершить работу программы");
            System.out.print("\n Введите номер операции : ");
            choice = sc.nextInt();
            sc.nextLine();
            User user= new User();
            System.out.println("Введите логин");
            String login = sc.next();
            sc.nextLine();
            System.out.println("Введите пароль");
            String password = sc.next();
            sc.nextLine();
                switch (choice) {
                    case 1 -> userController.greateUser(login,password);
                    case 2 -> userController.updateUser(login,password);
                    case 3 -> userController.deleteUser(login,password);
                    case 4 -> accountController.getBalance(login,password);
                    case 5 -> {System.out.println("Введите сумму пополнения");
                        double money = sc.nextDouble();
                    accountController.increaseBalance(login,password,money);}
                    case 6 -> {System.out.println("Введите сумму пополнения");
                        double money = sc.nextDouble();
                        accountController.reduceBalance(login,password,money);}
                    case 7 -> userController.getMyHistory(login,password);
                    case 8 -> {
                        System.out.println("\nThank you for choosing Bank Of Java.");
                        System.exit(1);
                        break;
                    }
                    default -> System.out.println("Неправильный выбор !");
                }
        }




    }


}
