package com.igr.walletservice.service.impl;

import com.igr.walletservice.entity.History;
import com.igr.walletservice.repository.AccountRepository;
import com.igr.walletservice.repository.HistoryRepository;
import com.igr.walletservice.service.AccountService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
/** Реализация сервиса счетов */
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private HistoryRepository historyRepository;

    @Override
    public double getBalance(String user) {
        return accountRepository.getBalance(user);
    }

    @Override
    public double increaseBalance(double money,String user) {
        double beforeBalance = accountRepository.getBalance(user);
        if (accountRepository.increaseBalance(money, user)>0) {
            History history= new History();
            history.setLocalDateTime(LocalDateTime.now());
            history.setBeforeBalance(beforeBalance);
            history.setAfterBalance(accountRepository.getBalance(user));
            historyRepository.save(history);
            return accountRepository.getBalance(user);
        }
        return 0;
    }

    @Override
    public double reduceBalance(double money,String user) {
        double beforeBalance = accountRepository.getBalance(user);
         if (accountRepository.reduceBalance(money, user)==1) {
            History history= new History();
            history.setLocalDateTime(LocalDateTime.now());
            history.setBeforeBalance(beforeBalance);
            history.setAfterBalance(accountRepository.getBalance(user));
            historyRepository.save(history);
             return accountRepository.getBalance(user);
        }
         return accountRepository.reduceBalance(money, user);
    }
}
