package com.reymitech.app.bankaccount.account.application.usecases;

import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllAccountsUseCase {
    private final IAccountRepositoryPort accountRepositoryPort;

    public List<Account> execute() {
        return accountRepositoryPort.findAllAccounts();
    }
}
