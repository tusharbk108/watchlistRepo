package com.atyeti.tradingApp.repository;

import com.atyeti.tradingApp.models.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserModel,String> {


    UserModel findByEmail(String email);
}
