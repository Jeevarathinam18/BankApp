package com.example.bankapp.controller;

import com.example.bankapp.model.Transaction;
import com.example.bankapp.service.CsvExportService;
import com.example.bankapp.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    @Autowired
    CsvExportService csvExportService;

//    @GetMapping("/user/{userId}")
//    public List<Transaction> getTransactionsByUserId(UUID userId){
//        return transactionServiceImpl.getTransactionByUserId(userId);
//    }

    @GetMapping(value = "/export", produces = "text/csv")
    public ResponseEntity<InputStreamResource> exportTransaction(@RequestParam String accountNumber){
        List<Transaction> transactions = transactionServiceImpl.getTransactionByUserId(accountNumber);
        ByteArrayInputStream in = csvExportService.exportTransactionsToCsv(transactions);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=transactions.csv");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(in));
    }

}
