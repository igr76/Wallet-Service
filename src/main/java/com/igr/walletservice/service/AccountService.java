package com.igr.walletservice.service;

public interface AccountService {
    public double getBalance(String user);
    public double increaseBalance(double money,String user);
    public double reduceBalance(double money,String user);
}
