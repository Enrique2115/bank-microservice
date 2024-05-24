package com.reymitech.app.bankaccount.bank.application.usecase;

import com.reymitech.app.bankaccount.bank.domain.models.Bank;
import com.reymitech.app.bankaccount.bank.domain.port.IBankRepositoryPort;
import com.reymitech.app.bankaccount.bank.infraestructure.request.CreateBankRequest;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.utils.enums.MantenimentFee;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class CreateBankUseCase {

    private final IBankRepositoryPort bankRepositoryPort;

    public Bank execute(CreateBankRequest createBankRequest) {
        try {
            bankRepositoryPort.validExistBank();

            Bank bank = Bank.builder()
                    .bankName(createBankRequest.getBankName())
                    .bankAddress(createBankRequest.getBankAddress())
                    .savingsAccountsLimit(createBankRequest.getSavingsAccountsLimit())
                    .checkingAccountsLimit(createBankRequest.getCheckingAccountsLimit())
                    .fixedTermAccountsLimit(createBankRequest.getFixedTermAccountsLimit())
                    .active(Active.ACTIVE)
                    .build();

            return bankRepositoryPort.save(bank);
        } catch (Exception e) {
            throw new GenericErrorResponse(String.format(Constants.EXCEPTION_BANK_CREATE, createBankRequest.getBankName()), HttpStatus.BAD_REQUEST);
        }

    }
}
