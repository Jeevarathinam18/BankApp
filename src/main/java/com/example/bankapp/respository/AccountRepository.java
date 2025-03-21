package com.example.bankapp.respository;

import com.example.bankapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Account a WHERE TRIM(a.accountNumber) = TRIM(:accountNumber)")
    boolean existsByAccountNumber(@Param("accountNumber") String accountNumber);

    @Query("SELECT a FROM Account a WHERE TRIM(a.accountNumber) = TRIM(:accountNumber)")
    Optional<Account> findByAccountNumber(@Param("accountNumber") String accountNumber);


}
