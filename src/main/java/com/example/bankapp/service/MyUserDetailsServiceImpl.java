package com.example.bankapp.service;

import com.example.bankapp.api.MyUserDetailsServiceApi;
import com.example.bankapp.model.UserPrincipal;
import com.example.bankapp.model.Users;
import com.example.bankapp.respository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService, MyUserDetailsServiceApi {

    @Autowired
    UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUsername(username);
        if(user == null){
            System.out.println("USER NOT FOUND IN LOAD USER NAME");
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrincipal(user);
    }
}
