package com.igr.walletservice.repository;

import com.igr.walletservice.entity.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {
    List<History> historyList ;

    public void save(History history) {
        historyList.add(history);
    }

    public List<History>  getMyHistory(String login) {
        List<History> historyListLogin = new ArrayList<>();
        for (History e :
                historyList) {
            if (e.getLogin() == login) {
                historyListLogin.add(e);
            }
        }
        return historyListLogin;
    }
}
