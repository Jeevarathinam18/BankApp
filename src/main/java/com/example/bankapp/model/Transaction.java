package com.example.bankapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="transaction")

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_account_number", nullable = true)
    private String fromAccountNumber;

    @Column(name = "to_account_number", nullable = true)
    private String toAccountNumber;

    @Column(name = "particulars", nullable = false)
    private String particulars;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String type;

    public Transaction() {

    }

    public Transaction(String fromAccountNumber, String toAccountNumber, String particulars, Double amount, Double balance, LocalDateTime timestamp, String type) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.particulars = particulars;
        this.amount = amount;
        this.balance = balance;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getToAccount() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccount) {
        this.toAccountNumber = toAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}