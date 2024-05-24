package com.reymitech.app.bankaccount.account.application.usecases;

import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import com.reymitech.app.bankaccount.account.infraestructure.request.UpdateAccountBalanceRequest;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class UpdateAccountBalanceUseCase {

    private final IAccountRepositoryPort accountRepository;

    public Account execute(final UpdateAccountBalanceRequest request) {
        Account account = accountRepository.findAccountByAccountNumber(request.getNumAccount());

        if (account == null) {
            throw new GenericErrorResponse(
                    String.format(Constants.EXCEPTION_NOT_FOUND_ACCOUNT, request.getNumAccount()), HttpStatus.NOT_FOUND
            );
        }

        account.setAccountBalance(request.getBalance());

        if (account.getAccountBalance() < 0) {
            throw new GenericErrorResponse(Constants.EXCEPTION_BALANCE_NEGATIVE, HttpStatus.BAD_REQUEST);
        }

        return accountRepository.save(account);
    }
}
