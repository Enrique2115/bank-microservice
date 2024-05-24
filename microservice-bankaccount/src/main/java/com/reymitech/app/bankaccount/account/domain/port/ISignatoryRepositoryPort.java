package com.reymitech.app.bankaccount.account.domain.port;

import com.reymitech.app.bankaccount.account.domain.models.Signatory;

public interface ISignatoryRepositoryPort {

    Signatory save(Signatory signatory);

    Signatory findSignatoryById(String id);
}
