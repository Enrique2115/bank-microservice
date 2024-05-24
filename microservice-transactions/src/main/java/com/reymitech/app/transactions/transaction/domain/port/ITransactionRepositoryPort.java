package com.reymitech.app.transactions.transaction.domain.port;

import com.reymitech.app.transactions.transaction.domain.models.Transaction;

import java.util.List;

public interface ITransactionRepositoryPort {
    void save(Transaction transaction);

    Transaction findById(String id);

    List<Transaction> findByAccountId(String accountId);

    List<Transaction> findAll();
}
