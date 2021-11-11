package com.atyeti.tradingApp.service;

import com.atyeti.tradingApp.models.CompanyModel;
import com.atyeti.tradingApp.models.HistoryModel;
import com.atyeti.tradingApp.models.MySharesModel;
import com.atyeti.tradingApp.models.UserModel;
import com.atyeti.tradingApp.repository.*;
import com.atyeti.tradingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class MyShareService {

    @Autowired
    MySharesRepository mySharesRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepository;


    public Map<String, String> buy(@RequestBody Map<String, Object> userInput) {
        String email = (String) userInput.get("email");
        int companyId = Integer.parseInt(userInput.get("companyId").toString());
        int qty = Integer.parseInt(userInput.get("quantity").toString());

        HashMap<String, String> map = new HashMap<>();

        List<MySharesModel> allShares = (List<MySharesModel>) mySharesRepository.findAll();
        List<MySharesModel> myShares = new ArrayList<MySharesModel>();

        try {
            UserModel userModel = userRepository.findByEmail(email);
            CompanyModel companyModel = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("Company not Found"));

            Iterator<MySharesModel> iter2 = allShares.iterator();
            while (iter2.hasNext()) {
                MySharesModel ms = (MySharesModel) iter2.next();
                if (email.equals(ms.getUser_id())) {
                    if (ms.getCompany_id() == companyId)
                        myShares.add(ms);
                }
            }

            if (userModel.getAmount_left() <= (qty * companyModel.getCurrent_rate())) {
                map.put("status", "insufficient balance");
                return map;
            } else if (!myShares.isEmpty()) {
                int userAmount = userModel.getAmount_left();
                int currentAmount = userAmount - qty * companyModel.getCurrent_rate();

                int userQty = myShares.get(0).getQuantity();
                int currentQty = userQty + qty;

                myShares.get(0).setQuantity(currentQty);
                myShares.get(0).setBought_rate(companyModel.getCurrent_rate());
                mySharesRepository.save(myShares.get(0));

                userModel.setAmount_left(currentAmount);
                userRepository.save(userModel);

                map.put("status", "success");
                return map;
            } else {
                int userAmount = userModel.getAmount_left();
                int currentAmount = userAmount - qty * companyModel.getCurrent_rate();

                userModel.setAmount_left(currentAmount);
                userRepository.save(userModel);

                MySharesModel msm = new MySharesModel(companyId, companyModel.getName(), email,
                        companyModel.getOpen_rate(), companyModel.getClose_rate(), companyModel.getPeak_rate(),
                        companyModel.getLeast_rate(), companyModel.getCurrent_rate(), qty,
                        companyModel.getCurrent_rate(), companyModel.getYear_low(), companyModel.getYear_high(),
                        companyModel.getMarket_cap(), companyModel.getP_e_ratio(), companyModel.getVolume());
                mySharesRepository.save(msm);


            }
        } catch (Exception e) {
            map.put("status", e.getMessage());
            return map;
        }
        map.put("status", "success");
        return map;
    }

   //module for selling share
    public Map<String, String> sell( Map<String, Object> payload)
    {
        String email = (String) payload.get("email");
        int companyId = Integer.parseInt(payload.get("companyId").toString());
        int qty = Integer.parseInt(payload.get("quantity").toString());
        HashMap<String, String> map = new HashMap<>();

        List<UserModel> users = (List<UserModel>) userRepository.findAll();
        List<UserModel> user = new ArrayList<UserModel>();

        List<MySharesModel> allShares = (List<MySharesModel>)mySharesRepository.findAll();
        List<MySharesModel> myShares = new ArrayList<MySharesModel>();

        List<CompanyModel> allCompany = (List<CompanyModel>)companyRepository.findAll();
        List<CompanyModel> company = new ArrayList<CompanyModel>();

        try
        {
            Iterator<UserModel> iter = users.iterator();
            while(iter.hasNext())
            {
                UserModel us = (UserModel) iter.next();
                if(email.equals(us.getEmail()))
                    user.add(us);
            }

            Iterator<MySharesModel> iter2 = allShares.iterator();
            while(iter2.hasNext())
            {
                MySharesModel ms = (MySharesModel) iter2.next();
                if(email.equals(ms.getUser_id()))
                {
                    if(ms.getCompany_id() == companyId)
                        myShares.add(ms);
                }
            }

            Iterator<CompanyModel> iter3 = allCompany.iterator();
            while(iter3.hasNext())
            {
                CompanyModel cm = (CompanyModel) iter3.next();
                if(companyId == cm.getCompany_id())
                    company.add(cm);
            }

            int userAmount = user.get(0).getAmount_left();
            int currentAmount = userAmount + qty*company.get(0).getCurrent_rate();

            int userQty = myShares.get(0).getQuantity();
            int currentQty = userQty - qty;

            if(currentQty == 0)
            {
                mySharesRepository.delete(myShares.get(0));
            }
            else
            {
                myShares.get(0).setQuantity(currentQty);
                mySharesRepository.save(myShares.get(0));
            }
            user.get(0).setAmount_left(currentAmount);
            userRepository.save(user.get(0));

            map.put("status", "success");
            return map;

        }
        catch(Exception e)
        {
            map.put("status", e.getMessage());
            return map;
        }
    }

}
