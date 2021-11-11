package com.atyeti.tradingApp.controller;

import com.atyeti.tradingApp.models.HistoryModel;
import com.atyeti.tradingApp.models.WatchListModel;
import com.atyeti.tradingApp.service.HistoryService;
import com.atyeti.tradingApp.service.MyShareService;
import com.atyeti.tradingApp.service.UserService;
import com.atyeti.tradingApp.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins="*")
@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    MyShareService myShareService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private WatchlistService watchlistService;

    @PostMapping("/signin")
    public Map<String, String> login(@RequestBody Map<String, String> userInput) {

        return userService.login(userInput);

    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> userInputs) {

        return userService.register(userInputs);
    }

    @PostMapping("/buy")
    public Map<String, String> buyt(@RequestBody Map<String, Object> payload) {

        return myShareService.buy(payload);
    }

    @GetMapping("/history")
    public List<HistoryModel> transactionHistroy(@RequestParam("email") String email) {
        return historyService.transactionHistory(email);

    }

    @PostMapping("/sell")
    public Map<String, String> sell(@RequestBody Map<String, Object> payload)
    {
       return myShareService.sell(payload);
    }

    @GetMapping("/watch-list")
    public List<WatchListModel> watchList(@RequestParam("email") String email)
    {
       return watchlistService.watchList(email);
    }

    @GetMapping("/add-watchlist")
    public Map<String, String>  addWatchList(@RequestParam String email, @RequestParam int id)
    {
        return watchlistService.addWatchList(email,id);
    }

    @GetMapping("/remove-watchlist")
    public Map<String, String>  removeWatchList(@RequestParam String email, @RequestParam int id)
    {
       return watchlistService.removeWatchList(email,id);
    }
}


