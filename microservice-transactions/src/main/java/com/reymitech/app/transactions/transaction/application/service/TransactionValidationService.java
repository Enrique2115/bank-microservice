package com.reymitech.app.transactions.transaction.application.service;

import com.reymitech.app.transactions.transaction.domain.enums.TypeAccount;
import com.reymitech.app.transactions.transaction.domain.enums.TypeTransaction;
import com.reymitech.app.transactions.transaction.domain.models.Transaction;
import com.reymitech.app.transactions.transaction.domain.port.ITransactionValidationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionValidationService implements ITransactionValidationService {
    @Override
    public boolean validateTransactionLimit(TypeAccount accountType, Integer limit, List<Transaction> transactions) {

        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        if (accountType == TypeAccount.SAVINGS || accountType == TypeAccount.FIXED_TERM) {
            long countTransactions = transactions.stream()
                    .filter(transaction -> transaction.getCreatedAt().getMonthValue() == currentMonth)
                    .filter(transaction -> transaction.getCreatedAt().getYear() == currentYear)
                    .count();

            return countTransactions < limit;
        }

        return false;
    }

    @Override
    public boolean validateTransactionAmount(String typeTransaction, Double accountAmount, Double transactionAmount) {
        if (typeTransaction.equals(TypeTransaction.WITHDRAW.name())) {
            return !(transactionAmount > accountAmount);
        }
        return transactionAmount < accountAmount;
    }
}
