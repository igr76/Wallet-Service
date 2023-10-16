package com.igr.walletservice.service.impl;

import com.igr.walletservice.entity.History;
import com.igr.walletservice.repository.HistoryRepository;
import com.igr.walletservice.service.HistoryService;
import lombok.RequiredArgsConstructor;

import java.util.List;
/** Реализация сервиса истории */
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private HistoryRepository historyRepository;
    @Override
    public void getMyHistory(String login) {
        historyRepository.getMyHistory(login);
    }
}
