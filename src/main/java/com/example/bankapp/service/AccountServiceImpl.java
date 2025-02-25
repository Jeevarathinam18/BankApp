package com.example.bankapp.service;

import com.example.bankapp.Exception.InsufficientBalanceException;
import com.example.bankapp.api.AccountServiceApi;
import com.example.bankapp.model.Account;
import com.example.bankapp.model.TransactionRequest;
import com.example.bankapp.model.TransferRequest;
import com.example.bankapp.model.Users;
import com.example.bankapp.respository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountServiceApi {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    public void createAccount(Users user){

        String accountNumber = generateAccountNumber();

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setCreatedAt(LocalDateTime.now());
        account.setUser(user);
        account.setBalance(0.0);

        accountRepository.save(account);
        System.out.println("Account Created with account Number " + accountNumber);
    }

    private String generateAccountNumber() {

        Random random = new Random();

        String accountNumber;

        do{
            accountNumber = String.format("%10d", random.nextInt(1_000_000_000));
        }while (accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }

    @Transactional
    public String  deposit(TransactionRequest request) throws IllegalAccessException {

        String accountNumber = request.getAccountNumber().trim();
        Double amount = request.getAmount();

        Account account = getAccount(accountNumber);

        if(amount <= 0){
            throw new IllegalAccessException("Amount should be greater than 0");
        }

        Double newBalance = account.getBalance() + amount;

        account.setBalance(newBalance);
        accountRepository.save(account);

        transactionServiceImpl.createTransaction("DEPOSIT", null, accountNumber, amount, newBalance);
        return "Your amount " + amount +  "successfully deposited to your account, New balance " + newBalance;
    }

    @Transactional
    public String withDraw(TransactionRequest request) throws IllegalAccessException {

        String accountNumber = request.getAccountNumber();
        Double amount = request.getAmount();

        Account account = getAccount(accountNumber);
        Double balance = account.getBalance();

        if(balance < amount){
            throw new IllegalAccessException("You Balance is less than the require amount ");
        }

        double newBalance = balance - amount;
        account.setBalance(newBalance);
        accountRepository.save(account);

        transactionServiceImpl.createTransaction("WITHDRAW", accountNumber, null,  amount, newBalance);
        return "You have successfully withdrawn the requested amount " + amount + " Current balance " + newBalance;
    }

    public Double getBalance(String accountNumber) {

        Account account = getAccount(accountNumber);

        return account.getBalance();
    }

    private Account getAccount(String accountNumber){

         return accountRepository.findByAccountNumber(accountNumber.trim())
                .orElseThrow(() -> new RuntimeException("Account Not found with account Number " + accountNumber));

    }

    public Account getAccountDetails(String accountNumber) {

        return getAccount(accountNumber);
    }

    public String transferFund(TransferRequest request) throws InsufficientBalanceException, IllegalAccessException {

        String fromAccountNumber = request.getFromAccounNumber();
        String toAccountNumber = request.getToAccountNumber();
        Double amount = request.getAmount();

        Account fromAccount = getAccount(fromAccountNumber.trim());
        Account toACcount = getAccount(toAccountNumber.trim());

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in the source account");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toACcount.setBalance(toACcount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toACcount);

        transactionServiceImpl.createTransaction("TRANSFER", fromAccountNumber, toAccountNumber,  amount, fromAccount.getBalance());

        return "Fund was successfully transferred. Your new balance " + toACcount.getBalance();

    }
}
