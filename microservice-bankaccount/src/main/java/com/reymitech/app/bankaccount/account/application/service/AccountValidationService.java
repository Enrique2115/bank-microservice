package com.reymitech.app.bankaccount.account.application.service;

import com.reymitech.app.bankaccount.account.domain.dtos.CustomerDto;
import com.reymitech.app.bankaccount.account.domain.enums.AccountType;
import com.reymitech.app.bankaccount.account.domain.enums.CustomerType;
import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountValidationService;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountValidationService implements IAccountValidationService {
    @Override
    public boolean validatePersonalAccount(CustomerDto customer, List<Account> accountsCustomer) {
        if (!customer.getTypeCustomer().getName().equalsIgnoreCase(CustomerType.PERSONAL.name())) {
            throw new GenericErrorResponse(
                    String.format(
                            Constants.EXCEPTION_INVALID_CUSTOMER_TYPE_PERSONAL, customer.getTypeCustomer().getName()
                    ), HttpStatus.BAD_REQUEST
            );
        }

        long activeAccountCount = accountsCustomer
                .stream()
                .filter(account -> account.getActive().equals(Active.ACTIVE))
                .count();

        return activeAccountCount == 0;
    }

    @Override
    public boolean validateBusinessAccount(CustomerDto customer, String accountType) {
        if (!customer.getTypeCustomer().getName().equalsIgnoreCase(CustomerType.BUSINESS.name())) {
            throw new GenericErrorResponse(
                    String.format(
                            Constants.EXCEPTION_INVALID_CUSTOMER_TYPE_BUSINESS, customer.getTypeCustomer().getName()
                    ), HttpStatus.BAD_REQUEST
            );
        }

        return !accountType.equals(AccountType.SAVINGS.name()) && !accountType.equals(AccountType.FIXED_TERM.name());
    }
}
