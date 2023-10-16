package com.igr.walletservice;

import com.igr.walletservice.controller.AccountController;
import com.igr.walletservice.controller.UserController;
import com.igr.walletservice.jdbc.Liquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


public class WalletServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
        UserController userController = new UserController();
        AccountController accountController = new AccountController();
        Liquibase.LiquibaseStart();
        Scanner in = new Scanner(System.in);
        System.out.println("Меню кредитного сервиса: \n  1- создать пользователя" +
                " \n 2- обновить пользователя \n  3- удалить пользователя \n 4- получить" +
                "состояние баланса \n  5- пополнение баланса \n  6- снятие средств  \n" +
                "7- получить историю операций");
        int number = in.nextInt();
        boolean b = false;
        while (b) {
            switch (Integer.toString(number)) {
                case "1" -> userController.greateUser();
                case "2" -> userController.updateUser();
                case "3" -> userController.deleteUser();
                case "4" -> accountController.getBalance();
                case "5" -> accountController.increaseBalance();
                case "6" -> accountController.reduceBalance();
                case "7" -> userController.getMyHistory();
                default -> b=true;
            }
            System.out.println("222222");
        }


    }


}
