package com.reymitech.app.bankaccount.bank.application.usecase;

import com.reymitech.app.bankaccount.bank.domain.port.IBankRepositoryPort;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class DesactiveBankUseCase {

    private final IBankRepositoryPort bankRepositoryPort;

    public void execute() {
        bankRepositoryPort.deleteBank();
    }
}
