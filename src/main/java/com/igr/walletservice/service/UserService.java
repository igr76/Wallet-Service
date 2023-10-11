package com.igr.walletservice.service;

import com.igr.walletservice.entity.User;

public interface UserService {
    public boolean greateUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public boolean checkPassword(User user);

}
