package com.reymitech.app.bankaccount.bank.infraestructure.repository;

import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.bank.domain.models.Bank;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaBankRepository extends CrudRepository<Bank, Long> {

    Optional<Bank> findBankByActive(Active active);
}
