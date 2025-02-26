package com.example.bankapp.service;

import com.example.bankapp.api.TransactionServiceApi;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionServiceApi {
    @Autowired
    TransactionRepository repository;

    public List<Transaction> getTransactionByUserId(String accountNumber) {
        return repository.findByAccountNumber(accountNumber.trim());
    }

    public void createTransaction(String type, String fromAccountNumber, String toAccountNumber, Double amount, Double balance) throws IllegalAccessException {

        Transaction transaction = new Transaction();

        switch (type) {
            case "DEPOSIT":
                transaction.setFromAccountNumber(null);
                transaction.setToAccountNumber(toAccountNumber);
                transaction.setParticulars("Deposit to " + toAccountNumber);
                break;

            case "WITHDRAW":
                transaction.setFromAccountNumber(fromAccountNumber);
                transaction.setToAccountNumber(null);
                transaction.setParticulars("Withdraw From " + fromAccountNumber);
                break;

            case "TRANSFER":
                transaction.setFromAccountNumber(fromAccountNumber);
                transaction.setToAccountNumber(toAccountNumber);
                transaction.setParticulars("Transfer From " + fromAccountNumber + " to " + toAccountNumber);
                break;

            default:
                throw new IllegalAccessException("Not a valid choice");
        }
        transaction.setBalance(balance);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType(type);
        repository.save(transaction);
        }

}
