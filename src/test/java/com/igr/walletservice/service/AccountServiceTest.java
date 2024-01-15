package com.igr.walletservice.service;

import com.igr.walletservice.entity.User;
import com.igr.walletservice.repository.AccountRepository;
import com.igr.walletservice.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@Testcontainers
class AccountServiceTest {
    AccountServiceImpl accountService;
    AccountRepository accountRepository;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer= new PostgreSQLContainer<>
            ("postgres:14")
            .withDatabaseName("x")
            .withUsername("igr")
            .withPassword("111111")
            .withInitScript("changelog.xml");

    @Test
    void getBalance() {
        assertThat(accountService.getBalance("user1")).isEqualTo(1300.);
        verify(accountRepository, times(1));
    }

    @Test
    void increaseBalance() {
        assertThat(accountService.debit(100,"user1")).isEqualTo(1400.);
        verify(accountRepository, times(4));
    }

    @Test
    void reduceBalance() {
        assertThat(accountService.credit(100,"user1")).isEqualTo(1300.);
        verify(accountRepository, times(4));
    }
    @Test
    void reduceBalanceNegativeTest() {
        assertThat(accountService.credit(100000,"user1")).isEqualTo(0);
        verify(accountRepository, times(3));
    }
}