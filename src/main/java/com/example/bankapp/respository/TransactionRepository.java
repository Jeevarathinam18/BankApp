package com.example.bankapp.respository;

import com.example.bankapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    List<Transaction> findByUserId(UUID userId);
}
