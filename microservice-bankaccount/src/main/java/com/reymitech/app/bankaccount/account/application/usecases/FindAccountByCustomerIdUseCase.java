package com.reymitech.app.bankaccount.account.application.usecases;

import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAccountByCustomerIdUseCase {
    private final IAccountRepositoryPort accountRepositoryPort;

    public List<Account> execute(String customerId) {
        return accountRepositoryPort.findAccountByCustomerId(customerId);
    }
}
