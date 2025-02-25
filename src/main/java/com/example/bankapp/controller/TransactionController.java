package com.example.bankapp.controller;

import com.example.bankapp.model.Transaction;
import com.example.bankapp.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(UUID userId){
        return transactionServiceImpl.getTransactionByUserId(userId);
    }
}
