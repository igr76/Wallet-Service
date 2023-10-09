package com.igr.walletservice.service.impl;

import com.igr.walletservice.entity.History;
import com.igr.walletservice.repository.HistoryRepository;
import com.igr.walletservice.service.HistoryService;

import java.util.List;

public class HistoryServiceImpl implements HistoryService {
    private HistoryRepository historyRepository;
    @Override
    public List<History> getHistory(String login) {
        return historyRepository.getHistory(login);
    }
}
