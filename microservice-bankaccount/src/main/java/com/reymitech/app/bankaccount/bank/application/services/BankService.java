package com.reymitech.app.bankaccount.bank.application.services;

import com.reymitech.app.bankaccount.bank.application.usecase.CreateBankUseCase;
import com.reymitech.app.bankaccount.bank.application.usecase.DesactiveBankUseCase;
import com.reymitech.app.bankaccount.bank.application.usecase.FindBankUseCase;
import com.reymitech.app.bankaccount.bank.application.usecase.UpdateBankUseCase;
import com.reymitech.app.bankaccount.bank.domain.models.Bank;
import com.reymitech.app.bankaccount.bank.infraestructure.request.CreateBankRequest;
import com.reymitech.app.bankaccount.bank.infraestructure.request.UpdateBankRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService implements IBankService {

    private final CreateBankUseCase createBankUseCase;
    private final FindBankUseCase findBankUseCase;
    private final UpdateBankUseCase updateBankUseCase;
    private final DesactiveBankUseCase desactiveBankUseCase;

    @Override
    public Optional<Bank> getBank() {
        return Optional.ofNullable(findBankUseCase.execute());
    }

    @Override
    public Bank createBank(CreateBankRequest bank) {
        return createBankUseCase.execute(bank);
    }

    @Override
    public void updateBank(UpdateBankRequest bank) {
        updateBankUseCase.execute(bank);
    }

    @Override
    public void deleteBank() {
        desactiveBankUseCase.execute();
    }
}
