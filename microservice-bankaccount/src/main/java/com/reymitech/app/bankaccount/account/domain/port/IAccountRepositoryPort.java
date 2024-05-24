package com.reymitech.app.bankaccount.account.domain.port;

import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreateBusinessAccountRequest;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreatePersonalAccountRequest;

import java.util.List;

public interface IAccountRepositoryPort {

    Account findAccountById(String id);

    Account save(Account account);

    List<Account> findAllAccounts();

    List<Account> findAccountByCustomerId(String customerId);
}
