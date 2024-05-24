package com.reymitech.app.bankaccount.account.infraestructure.adapters;

import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import com.reymitech.app.bankaccount.account.infraestructure.repository.JpaAccountRepository;
import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements IAccountRepositoryPort {
    private final JpaAccountRepository accountRepository;

    @Override
    public Account findAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new GenericErrorResponse(String.format(Constants.EXCEPTION_NOT_FOUND_ACCOUNT, id), HttpStatus.NOT_FOUND));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAccountByActive(Active.ACTIVE);
    }

    @Override
    public List<Account> findAccountByCustomerId(String customerId) {
        return accountRepository.findAccountByCustomerId(customerId);
    }

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }

}
