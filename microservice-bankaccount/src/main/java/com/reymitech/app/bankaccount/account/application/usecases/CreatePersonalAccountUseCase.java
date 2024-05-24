package com.reymitech.app.bankaccount.account.application.usecases;

import com.reymitech.app.bankaccount.account.application.client.ICustomerServiceClient;
import com.reymitech.app.bankaccount.account.domain.port.IAccountValidationService;
import com.reymitech.app.bankaccount.account.domain.dtos.CustomerDto;
import com.reymitech.app.bankaccount.account.domain.enums.AccountType;
import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import com.reymitech.app.bankaccount.account.domain.port.ICreditCardNumberGenerator;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreatePersonalAccountRequest;
import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.enums.MantenimentFee;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.*;

@RequiredArgsConstructor
public class CreatePersonalAccountUseCase {

    private final IAccountRepositoryPort accountRepositoryPort;
    private final ICustomerServiceClient customerServiceClient;
    private final ICreditCardNumberGenerator ICreditCardNumberGenerator;
    private final IAccountValidationService accountValidationService;

    public Account execute(String document, CreatePersonalAccountRequest request) {
        CustomerDto customer = getCustomerByDocument(document);
        List<Account> accountsCustomer = accountRepositoryPort.findAccountByCustomerId(customer.getId());

        if (!accountValidationService.validatePersonalAccount(customer, accountsCustomer)) {
            throw new GenericErrorResponse(String.format(Constants.LIMIT_ACCOUNT_CREATED, customer.getUsername()), HttpStatus.BAD_REQUEST);
        }

        return createAccount(request, customer);

    }

    private Account createAccount(CreatePersonalAccountRequest request, CustomerDto customer) {
        String accountNumber = ICreditCardNumberGenerator.generate(Constants.BIN_CREDIT_CARD_PERSONAL, Constants.MAX_LENGTH_CREDIT_CARD);
        Date nextPaymentMantenimentDate = new Date();
        nextPaymentMantenimentDate.setTime(nextPaymentMantenimentDate.getTime() + (30L * 24 * 60 * 60 * 1000));

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountName(request.getNameAccount())
                .accountType(getAccountType(request.getTypeAccount()))
                .accountBalance(request.getBalance())
                .mantenimentFee(getManagementFee(request.getTypeAccount()))
                .nextPaymentMantenimentDate(nextPaymentMantenimentDate)
                .active(Active.ACTIVE)
                .customerId(customer.getId())
                .build();

        return accountRepositoryPort.save(account);
    }

    private AccountType getAccountType(String typeAccount) {
        Map<String, AccountType> accountTypeMap = new HashMap<>();
        accountTypeMap.put("savings", AccountType.SAVINGS);
        accountTypeMap.put("checking", AccountType.CHECKING);
        accountTypeMap.put("fixed_term", AccountType.FIXED_TERM);

        AccountType accountType = accountTypeMap.get(typeAccount.toLowerCase());

        if (accountType == null) {
            throw new GenericErrorResponse(String.format(Constants.EXCEPTION_INVALID_ACCOUNT_TYPE, typeAccount), HttpStatus.BAD_REQUEST);
        }

        return accountType;
    }

    private double getManagementFee(String typeAccount) {
        Map<String, Double> accountTypeMap = new HashMap<>();
        accountTypeMap.put("savings", MantenimentFee.SAVINGS.getValue());
        accountTypeMap.put("checking", MantenimentFee.CHECKING.getValue());
        accountTypeMap.put("fixed_term", MantenimentFee.FIXED_TERM.getValue());

        Double managementFee = accountTypeMap.get(typeAccount.toLowerCase());

        if (managementFee == null) {
            throw new GenericErrorResponse(String.format(Constants.EXCEPTION_INVALID_ACCOUNT_TYPE, typeAccount), HttpStatus.BAD_REQUEST);
        }

        return managementFee;
    }

    private CustomerDto getCustomerByDocument(String document) {
        return Optional.ofNullable(customerServiceClient.getCustomerByDocument(document))
                .orElseThrow(() -> new IllegalArgumentException(String.format(Constants.EXCEPTION_NOT_FOUND_CUSTOMER, document)));
    }
}
