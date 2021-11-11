package com.atyeti.tradingApp.service;

import com.atyeti.tradingApp.models.UserModel;
import com.atyeti.tradingApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Map<String, String> register(Map<String, String> userInput) {

        Map<String, String> response = new HashMap<>();
        String email = userInput.get("email");
        String password = userInput.get("password");
        long phone = Integer.parseInt(userInput.get("phone"));
        String name = userInput.get("username");
        UserModel userModel = new UserModel(name, email, password, phone);
        userRepository.save(userModel);
        response.put("status", "success");
        return response;

    }

    public static void main(String[] args) {
        Map<String, String> res = new HashMap<>();

        res.put("email", "admin@gmail.com");
        res.put("password", "admin");

        UserService us = new UserService();
        Map<String, String> map = us.login(res);
        System.out.println(map);
    }

    public Map<String, String> login(Map<String, String> userInput) {
        String email = (String) userInput.get("email");
        String password = (String) userInput.get("password");
        Map<String, String> response = new HashMap<String, String>();
        try {
            UserModel userModel = userRepository.findByEmail(email);
            if (userModel.getEmail().equals(email)) {

                if (userModel.getPassword().equals(password)) {
                    response.put("status", "success");
                    return response;
                } else {
                    response.put("status", "Incorrect credentials");
                }
            }

        } catch (Exception e) {
            response.put("status", "User doesn't exist");
            return response;
        }

        return response;
    }



}
