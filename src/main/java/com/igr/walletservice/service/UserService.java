package com.igr.walletservice.service;

import com.igr.walletservice.entity.User;
/**  сервис пользователей */
public interface UserService {
    /** Создать пользователя */
    public boolean greateUser(User user);
    /** Обновление пользователя */
    public boolean updateUser(User user);
    /** Удаление  пользователя */
    public boolean deleteUser(User user);
    /** Проверка пароля пользователя */
    public boolean checkPassword(User user);

}
