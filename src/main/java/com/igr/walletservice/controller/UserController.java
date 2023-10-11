package com.igr.walletservice.controller;

import com.igr.walletservice.entity.History;
import com.igr.walletservice.entity.User;
import com.igr.walletservice.service.HistoryService;
import com.igr.walletservice.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class UserController {
    private UserService userService;
    private HistoryService historyService;
    private AccountController accountController;

    public void start() {
        Scanner in = new Scanner(System.in);

        System.out.println("Меню кредитного сервиса: /n  1- создать пользователя" +
                " /n 2- обновить пользователя /n  3- удалить пользователя /n 4- получить" +
                "состояние баланса /n  5- пополнение баланса /n  6- снятие средств  /n" +
                "7- получить историю операций");
        int number = in.nextInt();
        boolean b = false;
        while (b) {
            switch (Integer.toString(number)) {
                case "1" -> greateUser();
                case "2" -> updateUser();
                case "3" -> deleteUser();
                case "4" -> accountController.getBalance();
                case "5" -> accountController.increaseBalance();
                case "6" -> accountController.reduceBalance();
                case "7" -> getMyHistory();
                default -> b=true;
            }
        }
    }

    public void greateUser() {
        System.out.println("1111111");
        Scanner in = new Scanner(System.in);
        User user= new User();
        System.out.println("Введите логин");
        user.setLogin(in.next());
        System.out.println("Введите пароль");
        user.setPassword(in.next());
        if (userService.checkPassword(user)) {
            if (userService.greateUser(user)) {
                System.out.println("Пользователь успешно сохранен");
            }else System.out.println("Пользователь с таким именем уже существует, создание невозможно");
        }else System.out.println("Неверный пароль");
    }
    public void updateUser() {
        Scanner in = new Scanner(System.in);
        User user= new User();
        System.out.println("Введите логин");
        user.setLogin(in.next());
        System.out.println("Введите пароль");
        user.setPassword(in.next());
        if (userService.checkPassword(user)) {
            if (userService.updateUser(user)) {
                System.out.println("Пользователь успешно обновлен");
            }else System.out.println("Обновление невозможно это не ваш логин ");
        }else System.out.println("Неверный пароль");


    }
    public void deleteUser() {
        Scanner in = new Scanner(System.in);
        User user= new User();
        System.out.println("Введите логин");
        user.setLogin(in.next());
        System.out.println("Введите пароль");
        user.setPassword(in.next());
        if (userService.checkPassword(user)) {
            if (userService.updateUser(user)) {
                System.out.println("Пользователь успешно удален");
            }else System.out.println("Удаление невозможно это не ваш логин ");
        }else System.out.println("Неверный пароль");

    }

    public void getMyHistory() {
        Scanner in = new Scanner(System.in);
        User user= new User();
        System.out.println("Введите логин");
        user.setLogin(in.next());
        System.out.println("Введите пароль");
        user.setPassword(in.next());
        if (userService.checkPassword(user)) {
            List<History> historyList = historyService.getMyHistory(user.getLogin());
            for (History e :
                    historyList) {
                System.out.println(e.getLocalDateTime()+ "счет был: "+ e.getBeforeBalance()
                +"счёт стал: "+e.getAfterBalance());
            }
        }else System.out.println("Неверный пароль");
    }
}
