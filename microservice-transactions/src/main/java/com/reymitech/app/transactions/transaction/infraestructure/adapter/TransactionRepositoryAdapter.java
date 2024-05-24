package com.reymitech.app.transactions.transaction.infraestructure.adapter;

import com.reymitech.app.transactions.transaction.domain.models.Transaction;
import com.reymitech.app.transactions.transaction.domain.port.ITransactionRepositoryPort;
import com.reymitech.app.transactions.transaction.infraestructure.repository.JpaTransactionRepository;
import com.reymitech.app.transactions.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements ITransactionRepositoryPort {
    private final JpaTransactionRepository jpaTransactionRepository;

    @Override
    public void save(Transaction transaction) {
        jpaTransactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(String id) {
        return jpaTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException(Constants.EXCEPTION_TRANSACTION_NOT_FOUND));
    }

    @Override
    public List<Transaction> findByAccountId(String accountId) {
        return jpaTransactionRepository.findByAccountId(accountId);
    }

    @Override
    public List<Transaction> findAll() {
        return jpaTransactionRepository.findAll();
    }
}
