package com.reymitech.app.bankaccount.account.infraestructure.repository;

import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.utils.enums.Active;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAccountRepository extends CrudRepository<Account, String> {

    List<Account> findAccountByActive(Active active);

    List<Account> findAccountByCustomerId(String customerId);
}
