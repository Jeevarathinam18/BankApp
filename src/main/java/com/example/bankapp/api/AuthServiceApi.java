package com.example.bankapp.api;

import com.example.bankapp.model.LoginRequest;

public interface AuthServiceApi {
    String login(LoginRequest request);
}
