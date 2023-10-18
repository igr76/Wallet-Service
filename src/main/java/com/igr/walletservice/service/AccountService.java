package com.igr.walletservice.service;
/**  сервис счетов */
public interface AccountService {
    /**  Получить баланс счёта */
    public double getBalance(String user);
    /**  Пополнить счёт */
    public double increaseBalance(double money,String user);
    /**  Изьять средства со счёта */
    public double reduceBalance(double money,String user);
}
