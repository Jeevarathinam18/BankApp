package com.example.bankapp.api;

import com.example.bankapp.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionServiceApi {
    List<Transaction> getTransactionByUserId(UUID userId);
    void createTransaction(String type, String fromAccountNumber, String toAccountNumber, Double amount, Double balance) throws IllegalAccessException;
}
