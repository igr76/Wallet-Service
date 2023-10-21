package com.igr.walletservice.service;

import com.igr.walletservice.repository.HistoryRepository;
import com.igr.walletservice.service.impl.HistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@Testcontainers
class HistoryServiceTest {
    HistoryServiceImpl historyService;
    HistoryRepository historyRepository;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer= new PostgreSQLContainer<>
            ("postgres:14")
            .withDatabaseName("x")
            .withUsername("igr")
            .withPassword("111111")
            .withInitScript("changelog.xml");

    @Test
    void getMyHistory() {
        historyService.getMyHistory("user1");
        verify(historyRepository, times(1));

    }
}