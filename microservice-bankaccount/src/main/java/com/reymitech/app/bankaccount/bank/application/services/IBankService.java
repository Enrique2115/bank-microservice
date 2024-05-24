package com.reymitech.app.bankaccount.bank.application.services;

import com.reymitech.app.bankaccount.bank.domain.models.Bank;
import com.reymitech.app.bankaccount.bank.infraestructure.request.CreateBankRequest;
import com.reymitech.app.bankaccount.bank.infraestructure.request.UpdateBankRequest;

import java.util.Optional;

public interface IBankService {

    Optional<Bank> getBank();

    Bank createBank(CreateBankRequest bank);

    void updateBank(UpdateBankRequest bank);

    void deleteBank();
}
