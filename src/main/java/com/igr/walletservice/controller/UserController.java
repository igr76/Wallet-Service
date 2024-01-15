package com.igr.walletservice.controller;

import com.igr.walletservice.entity.History;
import com.igr.walletservice.entity.User;
import com.igr.walletservice.service.HistoryService;
import com.igr.walletservice.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;
/** Контроллер пользователей */
@RequiredArgsConstructor
public class UserController {
    private UserService userService;
    private HistoryService historyService;
    private AccountController accountController;
    public void greateUser(String login,String password) {
        User user= new User();
        user.setLogin(login);
        user.setPassword(password);
            if (userService.greateUser(user)) {
                System.out.println("Пользователь успешно сохранен");
            }else System.out.println("Пользователь с таким именем уже существует, создание невозможно");
    }
    public void updateUser(String login,String password) {
        User user= new User();
        user.setLogin(login);
        user.setPassword(password);
        if (userService.checkPassword(user)) {
            if (userService.updateUser(user)) {
                System.out.println("Пользователь успешно обновлен");
            }else System.out.println("Обновление невозможно это не ваш логин ");
        }else System.out.println("Неверный пароль");


    }
    public void deleteUser(String login,String password) {
        User user= new User();
        user.setLogin(login);
        user.setPassword(password);
        if (userService.checkPassword(user)) {
            if (userService.updateUser(user)) {
                System.out.println("Пользователь успешно удален");
            }else System.out.println("Удаление невозможно это не ваш логин ");
        }else System.out.println("Неверный пароль");

    }

    public void getMyHistory(String login,String password) {
        User user= new User();
        user.setLogin(login);
        user.setPassword(password);
        if (userService.checkPassword(user)) {
             historyService.getMyHistory(user.getLogin());

        }else System.out.println("Неверный пароль");
    }
}
