package com.igr.walletservice.service.impl;

import com.igr.walletservice.entity.History;
import com.igr.walletservice.repository.AccountRepository;
import com.igr.walletservice.repository.HistoryRepository;
import com.igr.walletservice.service.AccountService;

import java.time.LocalDateTime;

public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private HistoryRepository historyRepository;
    @Override
    public boolean increaseBalance(double money,String user) {
        double beforeBalance = accountRepository.getBalance(user);
        if (accountRepository.increaseBalance(money, user)) {
            History history= new History();
            history.setLocalDateTime(LocalDateTime.now());
            history.setBeforeBalance(beforeBalance);
            history.setAfterBalance(accountRepository.getBalance(user));
            historyRepository.save(history);
        }
        return false;
    }

    @Override
    public int reduceBalance(double money,String user) {
        double beforeBalance = accountRepository.getBalance(user);
         if (accountRepository.reduceBalance(money, user)==1) {
            History history= new History();
            history.setLocalDateTime(LocalDateTime.now());
            history.setBeforeBalance(beforeBalance);
            history.setAfterBalance(accountRepository.getBalance(user));
            historyRepository.save(history);
             return 1;
        }
         return accountRepository.reduceBalance(money, user);
    }
}
