package com.reymitech.app.bankaccount.bank.application.usecase;

import com.reymitech.app.bankaccount.bank.domain.models.Bank;
import com.reymitech.app.bankaccount.bank.domain.port.IBankRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindBankUseCase {

    private final IBankRepositoryPort bankRepositoryPort;

    public Bank execute() {
        return bankRepositoryPort.getBank();
    }
}
