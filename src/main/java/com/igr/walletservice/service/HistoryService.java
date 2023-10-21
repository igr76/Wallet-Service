package com.igr.walletservice.service;

import com.igr.walletservice.entity.History;

import java.util.List;
/**  сервис историй */
public interface HistoryService {
    /** Получить историю операций */
    public void getMyHistory(String login);
}
