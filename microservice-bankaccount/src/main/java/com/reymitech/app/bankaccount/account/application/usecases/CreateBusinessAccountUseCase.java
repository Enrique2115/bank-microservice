package com.reymitech.app.bankaccount.account.application.usecases;

import com.reymitech.app.bankaccount.account.application.client.ICustomerServiceClient;
import com.reymitech.app.bankaccount.account.application.service.AccountValidationService;
import com.reymitech.app.bankaccount.account.domain.dtos.CustomerDto;
import com.reymitech.app.bankaccount.account.domain.enums.AccountType;
import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.models.Signatory;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import com.reymitech.app.bankaccount.account.domain.port.ICreditCardNumberGenerator;
import com.reymitech.app.bankaccount.account.domain.port.ISignatoryRepositoryPort;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreateBusinessAccountRequest;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.utils.enums.MantenimentFee;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateBusinessAccountUseCase {

    private final IAccountRepositoryPort accountRepositoryPort;
    private final ISignatoryRepositoryPort signatoryRepositoryPort;
    private final ICustomerServiceClient customerServiceClient;
    private final ICreditCardNumberGenerator ICreditCardNumberGenerator;
    private final AccountValidationService accountValidationService;

    public Account execute(String customerId, CreateBusinessAccountRequest request) {
        CustomerDto customer = getCustomerById(customerId);

        if (!accountValidationService.validateBusinessAccount(customer, request.getTypeAccount())) {
            throw new GenericErrorResponse(
                    String.format(
                            Constants.EXCEPTION_BUSINESS_ACCOUNT, customer.getTypeCustomer().getName()
                    ), HttpStatus.BAD_REQUEST
            );
        }

        return createAccount(request, customer);
    }

    private Account createAccount(CreateBusinessAccountRequest request, CustomerDto customer) {
        String accountNumber = ICreditCardNumberGenerator
                .generate(Constants.BIN_CREDIT_CARD_BUSINESS, Constants.MAX_LENGTH_CREDIT_CARD);
        Date nextPaymentMantenimentDate = new Date();
        nextPaymentMantenimentDate.setTime(nextPaymentMantenimentDate.getTime() + (30L * 24 * 60 * 60 * 1000));

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .accountName(request.getNameAccount())
                .accountType(AccountType.CHECKING)
                .accountBalance(request.getBalance())
                .mantenimentFee(MantenimentFee.FIXED_TERM.getValue())
                .nextPaymentMantenimentDate(nextPaymentMantenimentDate)
                .active(Active.ACTIVE)
                .customerId(customer.getId())
                .build();

        if(request.getSignatories() != null) {
            List<Signatory> signatories = request.getSignatories().stream()
                    .map(this::apply)
                    .collect(Collectors.toList());
            account.setSignatory(signatories);
        }

        return accountRepositoryPort.save(account);
    }

    private CustomerDto getCustomerById(String document) {
        return Optional.ofNullable(customerServiceClient.getCustomerByDocument(document))
                .orElseThrow(() -> new GenericErrorResponse(
                        String.format(Constants.EXCEPTION_NOT_FOUND_CUSTOMER, document), HttpStatus.NOT_FOUND
                ));
    }

    private Signatory apply(Signatory signatory) {
        return signatoryRepositoryPort.save(new Signatory(signatory.getName(), signatory.getEmail()));
    }
}
