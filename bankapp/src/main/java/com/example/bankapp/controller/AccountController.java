package com.example.bankapp.controller;

import com.example.bankapp.Exception.InsufficientBalanceException;
import com.example.bankapp.model.Account;
import com.example.bankapp.model.TransactionRequest;
import com.example.bankapp.model.TransferRequest;
import com.example.bankapp.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @PostMapping("/deposit")
    public String deposit(@RequestBody TransactionRequest request) throws IllegalAccessException {

        return accountServiceImpl.deposit(request);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody TransactionRequest request)throws IllegalAccessException{
        return accountServiceImpl.withDraw(request);
    }

    @GetMapping("/{accountNumber}/balance")
    public Double balance(@PathVariable String accountNumber){
        return accountServiceImpl.getBalance(accountNumber);
    }

    @GetMapping("/{accountNumber}")
    public Account getAccountDetails(@PathVariable String accountNumber){
        return accountServiceImpl.getAccountDetails(accountNumber);
    }

    @PostMapping("/transfer")
    public String transferFund(@RequestBody TransferRequest request) throws InsufficientBalanceException, IllegalAccessException {
        return accountServiceImpl.transferFund(request);
    }
}
