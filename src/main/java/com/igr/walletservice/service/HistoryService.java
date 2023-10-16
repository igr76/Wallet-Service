package com.igr.walletservice.service;

import com.igr.walletservice.entity.History;

import java.util.List;

public interface HistoryService {
    public List<History> getMyHistory(String login);
}
