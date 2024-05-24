package com.reymitech.app.transactions.transaction.infraestructure.repository;

import com.reymitech.app.transactions.transaction.domain.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTransactionRepository extends CrudRepository<Transaction, String> {

    List<Transaction> findByAccountId(String accountId);

    List<Transaction> findAll();
}
