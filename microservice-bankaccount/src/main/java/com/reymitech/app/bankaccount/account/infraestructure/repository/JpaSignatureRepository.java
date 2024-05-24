package com.reymitech.app.bankaccount.account.infraestructure.repository;

import com.reymitech.app.bankaccount.account.domain.models.Signatory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSignatureRepository extends CrudRepository<Signatory, String> {
}
