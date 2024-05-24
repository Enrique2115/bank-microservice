package com.reymitech.app.bankaccount.bank.application.usecase;

import com.reymitech.app.bankaccount.bank.domain.port.IBankRepositoryPort;
import com.reymitech.app.bankaccount.bank.infraestructure.request.UpdateBankRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateBankUseCase {
    private final IBankRepositoryPort bankRepositoryPort;

    public void execute(UpdateBankRequest updateBankRequest) {
        bankRepositoryPort.updateBank(updateBankRequest);
    }
}
