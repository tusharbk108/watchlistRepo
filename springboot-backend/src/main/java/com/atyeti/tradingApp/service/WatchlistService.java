package com.atyeti.tradingApp.service;

import com.atyeti.tradingApp.models.CompanyModel;
import com.atyeti.tradingApp.models.WatchListModel;
import com.atyeti.tradingApp.repository.CompanyRepository;
import com.atyeti.tradingApp.repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class WatchlistService {

    @Autowired
    private WatchlistRepository watchListRepository;

    @Autowired
    private CompanyRepository companyRepository;


    public List<WatchListModel> watchList( String email)
    {
        List<WatchListModel> allWatchList = (List<WatchListModel>)watchListRepository.findAll();
        List<WatchListModel> myWatchList = new ArrayList<WatchListModel>();
        try
        {
            Iterator<WatchListModel> iter = allWatchList.iterator();
            while(iter.hasNext())
            {
                WatchListModel wl = (WatchListModel) iter.next();
                if(email.equals(wl.getUser_id()))
                {
                    System.out.print(wl);
                    myWatchList.add(wl);
                }
            }
        }
        catch(Exception e)
        {	System.out.print("Exception : ");
            System.out.print(e.getMessage());
        }

        return myWatchList;
    }

    public Map<String, String> addWatchList( String email, int id)
    {
        List<CompanyModel> allCompany = (List<CompanyModel>)companyRepository.findAll();
        List<WatchListModel> allWatchList = (List<WatchListModel>)watchListRepository.findAll();

        try
        {
            Iterator<WatchListModel> iter1 = allWatchList.iterator();
            while(iter1.hasNext())
            {
                WatchListModel wl = (WatchListModel) iter1.next();
                if(email.equals(wl.getUser_id())&&(id == wl.getCompany_id()))
                {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("status", "company exist");
                    return map;
                }
            }

            Iterator<CompanyModel> iter2 = allCompany.iterator();
            while(iter2.hasNext())
            {
                CompanyModel cm = (CompanyModel) iter2.next();
                if(id == cm.getCompany_id())
                {
                    WatchListModel wl = new WatchListModel(cm.getCompany_id(),cm.getName(),email,cm.getOpen_rate(),cm.getClose_rate(),cm.getPeak_rate(),cm.getLeast_rate(),cm.getCurrent_rate(),cm.getYear_low(),cm.getYear_high(),cm.getMarket_cap(),cm.getP_e_ratio(),cm.getVolume());
                    watchListRepository.save(wl);
                }
            }
        }
        catch(Exception e)
        {
            HashMap<String, String> map = new HashMap<>();
            map.put("status", e.getMessage());
            return map;
        }

        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        return map;
    }

    public Map<String, String>  removeWatchList( String email,  int id)
    {
        HashMap<String, String> map = new HashMap<>();
        List<CompanyModel> allCompany = (List<CompanyModel>)companyRepository.findAll();
        List<WatchListModel> allWatchList = (List<WatchListModel>)watchListRepository.findAll();

        try
        {
            Iterator<WatchListModel> iter1 = allWatchList.iterator();
            while(iter1.hasNext())
            {
                WatchListModel wl = (WatchListModel) iter1.next();
                if(email.equals(wl.getUser_id())&&(id == wl.getCompany_id()))
                {
                    watchListRepository.delete(wl);
                    map.put("status", "success");
                    return map;
                }
            }


        }
        catch(Exception e)
        {
            map.put("status", e.getMessage());
            return map;
        }

        map.put("status", "success");
        return map;
    }

}
