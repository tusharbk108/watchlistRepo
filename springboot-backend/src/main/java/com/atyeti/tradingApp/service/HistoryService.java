package com.atyeti.tradingApp.service;


import com.atyeti.tradingApp.models.HistoryModel;
import com.atyeti.tradingApp.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    public List<HistoryModel> transactionHistory(@RequestParam("email") String email) {
        List<HistoryModel> allHistoryModel = (List<HistoryModel>) historyRepository.findAll();
        List<HistoryModel> myHistoryModel = new ArrayList<HistoryModel>();

        try {
            Iterator<HistoryModel> iter = allHistoryModel.iterator();
            while (iter.hasNext()) {
                HistoryModel h = (HistoryModel) iter.next();
                if (email.equals(h.getUser_id())) {
                    myHistoryModel.add(h);
                }

            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return myHistoryModel;

    }


}
