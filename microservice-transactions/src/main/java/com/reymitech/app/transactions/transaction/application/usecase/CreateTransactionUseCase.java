package com.reymitech.app.transactions.transaction.application.usecase;

import com.reymitech.app.transactions.transaction.application.client.IBankAccountService;
import com.reymitech.app.transactions.transaction.domain.dtos.AccountDto;
import com.reymitech.app.transactions.transaction.domain.enums.TypeAccount;
import com.reymitech.app.transactions.transaction.domain.enums.TypeTransaction;
import com.reymitech.app.transactions.transaction.domain.models.Transaction;
import com.reymitech.app.transactions.transaction.domain.port.ITransactionRepositoryPort;
import com.reymitech.app.transactions.transaction.domain.port.ITransactionValidationService;
import com.reymitech.app.transactions.transaction.infraestructure.request.CreateTransactionRequest;
import com.reymitech.app.transactions.transaction.infraestructure.request.UpdateAccountBalanceRequest;
import com.reymitech.app.transactions.utils.Constants;
import com.reymitech.app.transactions.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class CreateTransactionUseCase {

    private final ITransactionRepositoryPort transactionRepositoryPort;
    private final ITransactionValidationService transactionValidationService;
    private final IBankAccountService accountService;

    public void execute(CreateTransactionRequest request) {
        AccountDto account = getCustomerByDocument(request.getAccountId());
        List<Transaction> transactions = transactionRepositoryPort.findByAccountId(account.getId());

        TypeAccount accountType = getAccountType(account.getAccountType());

        int bankLimit = getBank(accountType);

        if (!transactionValidationService.validateTransactionLimit(accountType, bankLimit, transactions)) {
            throw new IllegalArgumentException(String.format(Constants.EXCEPTION_TRANSACTION_LIMIT, accountType.name()));
        }

        Double accountBalance = Double.valueOf(account.getAccountBalance());

        if (!transactionValidationService.validateTransactionAmount(request.getType(), accountBalance, request.getAmount())) {
            throw new IllegalArgumentException(String.format(Constants.EXCEPTION_TRANSACTION_AMOUNT, accountBalance));
        }

        Transaction transaction = createTransaction(request, account, accountBalance);

        updateAccountBalance(account, accountBalance, request);

        transactionRepositoryPort.save(transaction);
    }

    private static Transaction createTransaction(CreateTransactionRequest request, AccountDto account, Double accountBalance) {
        return Transaction
                .builder()
                .accountId(account.getId())
                .amount(request.getAmount())
                .type(TypeTransaction.valueOf(request.getType()))
                .description(request.getDescription())
                .availableBalance(accountBalance)
                .build();
    }

    private int getBank(TypeAccount accountType) {

        if (accountType == TypeAccount.SAVINGS) {
            return accountService.getBank().getSavingsAccountsLimit();
        }
        return accountService.getBank().getFixedTermAccountsLimit();
    }

    private AccountDto getCustomerByDocument(String accountId) {
        return Optional.ofNullable(accountService.getAccountById(accountId))
                .orElseThrow(() -> new GenericErrorResponse(String.format(Constants.EXCEPTION_ACCOUNT_NOT_FOUND, accountId), HttpStatus.BAD_REQUEST));
    }

    private void updateAccountBalance(AccountDto account, Double accountBalance, CreateTransactionRequest request) {
        try {
            if (request.getType().equals(TypeTransaction.DEPOSIT.name())) {
                accountBalance += request.getAmount();
            } else if (request.getType().equals(TypeTransaction.WITHDRAW.name())) {
                accountBalance -= request.getAmount();
            }

            account.setAccountBalance(accountBalance.toString());

            UpdateAccountBalanceRequest request2 = new UpdateAccountBalanceRequest(account.getAccountNumber(), accountBalance);

            accountService.UpdateAccountBalanceRequest(request2);
        } catch (Exception e) {
            throw new GenericErrorResponse(String.format(Constants.EXCEPTION_ACCOUNT_UPDATE, account.getAccountNumber()), HttpStatus.BAD_REQUEST);
        }
    }

    private TypeAccount getAccountType(String typeAccount) {
        Map<String, TypeAccount> accountTypeMap = new HashMap<>();
        accountTypeMap.put("savings", TypeAccount.SAVINGS);
        accountTypeMap.put("checking", TypeAccount.CHECKING);
        accountTypeMap.put("fixed_term", TypeAccount.FIXED_TERM);

        TypeAccount accountType = accountTypeMap.get(typeAccount.toLowerCase());

        if (accountType == null) {
            throw new GenericErrorResponse(String.format(Constants.EXCEPTION_INVALID_ACCOUNT_TYPE, typeAccount), HttpStatus.BAD_REQUEST);
        }

        return accountType;
    }
}
