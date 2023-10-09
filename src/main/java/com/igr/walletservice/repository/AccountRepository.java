package com.igr.walletservice.repository;

import java.util.HashMap;

public class AccountRepository {
    HashMap<String ,Double> balanceMap;
    public boolean increaseBalance(double money, String user) {
        if (balanceMap.containsKey(user)) {
            balanceMap.put(user,money+balanceMap.get(user));
            return true;
        }return false;
    }

    public int reduceBalance(double money, String user) {
        if (balanceMap.containsKey(user)) {
            double balanceReal = balanceMap.get(user);
            if (balanceReal - money <= 0) {
                return 0;
            }
            balanceMap.put(user,balanceReal-money);
            return 1;
        } else return -1;
    }

    public double getBalance(String user) {
        return balanceMap.get(user);
    }
}
