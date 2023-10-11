package com.igr.walletservice.repository;

import java.util.HashMap;
/** Репозиторий счетов*/
public class AccountRepository {
    HashMap<String ,Double> balanceMap;
    public int increaseBalance(double money, String user) {
        if (balanceMap.containsKey(user)) {
            balanceMap.put(user,money+balanceMap.get(user));
            return 1;
        }return 0;
    }

    public double reduceBalance(double money, String user) {
        if (balanceMap.containsKey(user)) {
            double balanceReal = balanceMap.get(user);
            if (balanceReal - money <= 0) {
                return 0;
            }
            balanceMap.put(user,balanceReal-money);
            return getBalance(user);
        } else return -1;
    }

    public double getBalance(String user) {
        return balanceMap.get(user);
    }
}
