package com.reymitech.app.transactions.transaction.domain.port;

import com.reymitech.app.transactions.transaction.domain.enums.TypeAccount;
import com.reymitech.app.transactions.transaction.domain.models.Transaction;

import java.util.List;

public interface ITransactionValidationService {

    boolean validateTransactionLimit(TypeAccount accountType, Integer limit, List<Transaction> transactions);

    boolean validateTransactionAmount(String typeTransaction, Double accountBalance, Double transactionAmount);
}
