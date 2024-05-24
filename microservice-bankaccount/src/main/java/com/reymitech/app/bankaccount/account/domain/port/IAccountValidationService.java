package com.reymitech.app.bankaccount.account.domain.port;

import com.reymitech.app.bankaccount.account.domain.dtos.CustomerDto;
import com.reymitech.app.bankaccount.account.domain.models.Account;

import java.util.List;

public interface IAccountValidationService {

    boolean validatePersonalAccount(CustomerDto customerIdFromDb, List<Account> accountsCustomer);

    boolean validateBusinessAccount(CustomerDto customerIdFromDb, String typeAccount);
}
