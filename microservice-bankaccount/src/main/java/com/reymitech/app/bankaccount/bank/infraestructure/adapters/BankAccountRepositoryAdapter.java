package com.reymitech.app.bankaccount.bank.infraestructure.adapters;

import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.bank.domain.models.Bank;
import com.reymitech.app.bankaccount.bank.domain.port.IBankRepositoryPort;
import com.reymitech.app.bankaccount.bank.infraestructure.repository.JpaBankRepository;
import com.reymitech.app.bankaccount.bank.infraestructure.request.CreateBankRequest;
import com.reymitech.app.bankaccount.bank.infraestructure.request.UpdateBankRequest;
import com.reymitech.app.bankaccount.utils.enums.MantenimentFee;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class BankAccountRepositoryAdapter implements IBankRepositoryPort {

    private final JpaBankRepository jpaBankRepository;

    @Override
    public Bank getBank() {
        return jpaBankRepository.findBankByActive(Active.ACTIVE).orElseThrow(
                () -> new GenericErrorResponse(Constants.EXCEPTION_BANK_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Bank save(Bank bank) {
        try {
            return jpaBankRepository.save(bank);
        } catch (Exception e) {
            throw new GenericErrorResponse(String.format(Constants.EXCEPTION_BANK_CREATE, bank.getBankName()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void validExistBank() {
        jpaBankRepository.findBankByActive(Active.ACTIVE).ifPresent(
                bank -> {
                    throw new GenericErrorResponse(Constants.EXCEPTION_BANK_ALREADY_EXISTS, HttpStatus.CONFLICT);
                }
        );
    }

    @Override
    public void updateBank(UpdateBankRequest updateBankRequest) {
        try {
            jpaBankRepository.findBankByActive(Active.ACTIVE).ifPresent(
                    bank -> {
                        bank.setBankName(updateBankRequest.getName());
                        bank.setBankAddress(updateBankRequest.getAddress());
                        jpaBankRepository.save(bank);
                    }
            );
        } catch (Exception e) {
            throw new GenericErrorResponse(String.format(Constants.EXCEPTION_BANK_UPDATE, updateBankRequest.getName()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteBank() {
        try {
            jpaBankRepository.findBankByActive(Active.ACTIVE).ifPresent(
                    bank -> {
                        bank.setActive(Active.INACTIVE);
                        jpaBankRepository.save(bank);
                    }
            );
        } catch (Exception e) {
            throw new GenericErrorResponse(Constants.EXCEPTION_BANK_DELETE, HttpStatus.BAD_REQUEST);
        }
    }

}
