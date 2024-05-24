package com.reymitech.app.bankaccount.account.application.usecases;

import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAccountByIdUseCase {
    private final IAccountRepositoryPort accountRepositoryPort;

    public Account execute(String id) {
        return accountRepositoryPort.findAccountById(id);
    }
}
