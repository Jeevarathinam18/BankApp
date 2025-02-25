package com.example.bankapp.controller;

import com.example.bankapp.model.LoginRequest;
import com.example.bankapp.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){

        return authServiceImpl.login(loginRequest);
    }
}
