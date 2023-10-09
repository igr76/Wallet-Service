package com.igr.walletservice.service.impl;

import com.igr.walletservice.entity.User;
import com.igr.walletservice.repository.UserRepository;
import com.igr.walletservice.service.UserService;

public class UserServiceImpl implements UserService {
    private  UserRepository userRepository;
    @Override
    public boolean greateUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()) ==null) {
            userRepository.save(user);return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()).getLogin() ==user.getLogin()) {
            userRepository.save(user);return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()).getLogin() ==user.getLogin()) {
            userRepository.delete(user);return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(User user) {
        if (userRepository.checkPassword(user)) {
            return true;
        }
        return false;
    }
}
