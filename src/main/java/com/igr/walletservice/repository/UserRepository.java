package com.igr.walletservice.repository;

import com.igr.walletservice.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    List<User> userList = new ArrayList<>();
    public User findUserByLogin(String login) {
        for (User e : userList) {
            if (e.getLogin() == login) {
                return e;
            }
        }
        return null;
    }

    public void save(User user) {
        userList.add(user);
    }

    public void delete(User user) {
        User user1 = findUserByLogin(user.getLogin());
        userList.remove(user);
    }

    public boolean checkPassword(User user) {
        if (findUserByLogin(user.getLogin()).getPassword() == user.getPassword()) {
            return true;
        }
        return false;
    }
}
