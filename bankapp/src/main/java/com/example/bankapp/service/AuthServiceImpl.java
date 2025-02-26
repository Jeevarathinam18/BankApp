package com.example.bankapp.service;

import com.example.bankapp.api.AuthServiceApi;
import com.example.bankapp.model.LoginRequest;
import com.example.bankapp.respository.UsersRepo;
import com.example.bankapp.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthServiceApi {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;

    public String login(LoginRequest request){

        System.out.println("User have been loggedIn " + request.getUsername() + " " + request.getPassword());

        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        System.out.println("User have been loggedIn " + request.getUsername() + " " + request.getPassword());

        if(authentication.isAuthenticated()) {
            return jwtUtil.generateToken(request.getUsername());
        }else {
            return "Failed";
        }
    }
}
