package com.example.bankapp.api;

import com.example.bankapp.model.Users;

import java.util.List;

public interface AdminServiceApi {
    void registerUserAndCreateAccount(Users user);
    List<Users> getAllUsers();
}
