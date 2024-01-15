package com.igr.walletservice.service.impl;

import com.igr.walletservice.entity.History;
import com.igr.walletservice.repository.AccountRepository;
import com.igr.walletservice.repository.HistoryRepository;
import com.igr.walletservice.service.AccountService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/** Реализация сервиса счетов */
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private HistoryRepository historyRepository;

    @Override
    public double getBalance(String user) {
        return accountRepository.getBalance(user);
    }
  //  UUID y=UUID.randomUUID();

    @Override
    public double debit(double money,String user) {
        double beforeBalance = accountRepository.getBalance(user);
        if (accountRepository.increaseBalance(money, user)>0) {
            historyRepository.save(getHistory(beforeBalance,beforeBalance+money,user));
            return beforeBalance+money;
        }
        return 0;
    }

    @Override
    public double credit(double money,String user) {
        double beforeBalance = accountRepository.getBalance(user);
         if (accountRepository.reduceBalance(money, user)==1) {
            historyRepository.save(getHistory(beforeBalance,beforeBalance-money,user));
             return beforeBalance-money;
        }
         return 0;
    }

    static History getHistory(double beforeBalance,double afterBalance,String login) {
        History history= new History();
        history.setId(UUID.randomUUID().toString());
        history.setLocalDateTime(LocalDateTime.now());
        history.setLogin(login);
        history.setBeforeBalance(beforeBalance);
        history.setAfterBalance(afterBalance);
        return history;
    }
}
