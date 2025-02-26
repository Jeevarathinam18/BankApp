package com.example.bankapp.service;

import com.example.bankapp.api.AdminServiceApi;
import com.example.bankapp.model.Users;
import com.example.bankapp.respository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceApi {

    @Autowired
    UsersRepo repo;

    @Autowired
    AccountServiceImpl accountServiceImpl;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void registerUserAndCreateAccount(Users user){
        System.out.println("Service " + user.getUsername() + " " + user.getPassword());
        user.setRole(user.getRole());
        user.setPassword(encoder.encode(user.getPassword()));
        Users savedUser = repo.save(user);
        System.out.println("User Created");

        accountServiceImpl.createAccount(savedUser);

    }

    public List<Users> getAllUsers() {
        List<Users> list = new ArrayList<>();
        return list;
    }
}
