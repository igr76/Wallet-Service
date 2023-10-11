package com.igr.walletservice.controller;

import com.igr.walletservice.entity.User;
import com.igr.walletservice.service.AccountService;
import com.igr.walletservice.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;
/** Контроллер счетов */
@RequiredArgsConstructor
public class AccountController {
    private AccountService accountService;
    private UserService userService;

    public void getBalance() {
        Scanner in = new Scanner(System.in);
        User user= new User();
        System.out.println("Введите логин");
        user.setLogin(in.next());
        System.out.println("Введите пароль");
        if (userService.checkPassword(user)) {
            System.out.println("Состояние счета: "+ accountService.getBalance(user.getLogin()));
        }else System.out.println("Неверный пароль");
    }
    public void increaseBalance() {
        Scanner in = new Scanner(System.in);
        User user= new User();
        System.out.println("Введите логин");
        user.setLogin(in.next());
        System.out.println("Введите пароль");
        user.setPassword(in.next());
        System.out.println("Введите сумму пополнения");
        double money = in.nextDouble();
        if (userService.checkPassword(user)) {
            double balance = accountService.increaseBalance(money,user.getLogin());
            if (balance >0) {
                System.out.println("Сумма успешно зачисленна, состояние счета: "+balance);
            }else System.out.println("Сумма не была  зачисленна");
        }else System.out.println("Неверный пароль");
    }

    public void reduceBalance() {
        Scanner in = new Scanner(System.in);
        User user= new User();
        System.out.println("Введите логин");
        user.setLogin(in.next());
        System.out.println("Введите пароль");
        user.setPassword(in.next());
        System.out.println("Введите сумму отчисления");
        double money = in.nextDouble();
        if (userService.checkPassword(user)) {
            double balance = accountService.increaseBalance(money,user.getLogin());
            if (balance >0) {
                System.out.println("Сумма успешно снята, состояние счета: "+balance);
            } else if (balance == 0) {
                System.out.println("Сумма не была  снята, состояние счета меньше снимаемой суммы");
            }else System.out.println("Сумма не была  снята");
        }else System.out.println("Неверный пароль");
    }
}
