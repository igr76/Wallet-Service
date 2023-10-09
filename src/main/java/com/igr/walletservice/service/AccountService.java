package com.igr.walletservice.service;

public interface AccountService {
    public boolean increaseBalance(double money,String user);
    public int reduceBalance(double money,String user);
}
