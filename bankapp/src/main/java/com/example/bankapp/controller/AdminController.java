package com.example.bankapp.controller;

import com.example.bankapp.model.Users;
import com.example.bankapp.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminServiceImpl adminServiceImpl;

    @PostMapping("/users/register")
    public void registerUserAndCreateAccount(@RequestBody Users user){
        adminServiceImpl.registerUserAndCreateAccount(user);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return adminServiceImpl.getAllUsers();
    }
}
