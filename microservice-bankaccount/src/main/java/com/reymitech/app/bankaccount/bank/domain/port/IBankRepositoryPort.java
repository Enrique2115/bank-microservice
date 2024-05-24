package com.reymitech.app.bankaccount.bank.domain.port;

import com.reymitech.app.bankaccount.bank.domain.models.Bank;
import com.reymitech.app.bankaccount.bank.infraestructure.request.CreateBankRequest;
import com.reymitech.app.bankaccount.bank.infraestructure.request.UpdateBankRequest;

public interface IBankRepositoryPort {

    Bank getBank();

    Bank save(Bank bank);

    void validExistBank();

    void updateBank(UpdateBankRequest updateBankRequest);

    void deleteBank();
}
