package com.example.bankapp.model;


public class TransferRequest {

    private String fromAccounNumber;
    private String toAccountNumber;
    private Double amount;

    public TransferRequest(String fromAccountNumber, Double amount, String toAccountNumber) {
        this.fromAccounNumber = fromAccountNumber;
        this.amount = amount;
        this.toAccountNumber = toAccountNumber;
    }

    public String getFromAccounNumber() {
        return fromAccounNumber;
    }

    public void setFromAccountId(String fromAccounNumber) {
        this.fromAccounNumber = fromAccounNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
