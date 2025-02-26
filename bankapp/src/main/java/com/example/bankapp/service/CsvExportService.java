package com.example.bankapp.service;

import com.example.bankapp.model.Transaction;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;

@Service
public class CsvExportService {

        public ByteArrayInputStream exportTransactionsToCsv(List<Transaction> transactions) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                 CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(out))) {

                String[] header = {"ID", "Type", "From Account", "To Account", "Amount", "Balance", "Particulars", "Timestamp"};
                csvWriter.writeNext(header);

                for (Transaction transaction : transactions) {
                    String[] data = {
                            transaction.getId().toString(),
                            transaction.getType(),
                            transaction.getFromAccountNumber(),
                            transaction.getToAccountNumber(),
                            transaction.getAmount().toString(),
                            transaction.getBalance().toString(),
                            transaction.getParticulars(),
                            transaction.getTimestamp().toString()
                    };
                    csvWriter.writeNext(data);
                }

                csvWriter.flush();
                return new ByteArrayInputStream(out.toByteArray());

            } catch (IOException e) {
                throw new RuntimeException("Failed to export transactions to CSV", e);
            }
        }
}
