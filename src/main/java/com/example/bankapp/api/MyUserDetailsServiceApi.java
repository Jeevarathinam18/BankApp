package com.example.bankapp.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface MyUserDetailsServiceApi {
    UserDetails loadUserByUsername(String username);
}
