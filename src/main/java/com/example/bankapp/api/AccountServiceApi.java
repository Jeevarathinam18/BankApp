package com.example.bankapp.api;

import com.example.bankapp.Exception.InsufficientBalanceException;
import com.example.bankapp.model.Account;
import com.example.bankapp.model.TransactionRequest;
import com.example.bankapp.model.TransferRequest;
import com.example.bankapp.model.Users;

public interface AccountServiceApi {
    void createAccount(Users user);
    String deposit(TransactionRequest request) throws IllegalAccessException;
    String withDraw(TransactionRequest request) throws IllegalAccessException;
    Double getBalance(String accountNumber);
    Account getAccountDetails(String accountNumber);
    String transferFund(TransferRequest request) throws InsufficientBalanceException, IllegalAccessException;
}
