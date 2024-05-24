package com.reymitech.app.bankaccount.account.infraestructure.adapters;

import com.reymitech.app.bankaccount.account.domain.models.Signatory;
import com.reymitech.app.bankaccount.account.domain.port.ISignatoryRepositoryPort;
import com.reymitech.app.bankaccount.account.infraestructure.repository.JpaSignatureRepository;
import com.reymitech.app.bankaccount.utils.Constants;
import com.reymitech.app.bankaccount.utils.exceptions.GenericErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignatoryRepositoryAdapter implements ISignatoryRepositoryPort {

    private final JpaSignatureRepository signatureRepository;

    @Override
    public Signatory save(Signatory signatory) {
        return signatureRepository.save(signatory);
    }

    @Override
    public Signatory findSignatoryById(String id) {
        return signatureRepository.findById(id)
                .orElseThrow(() -> new GenericErrorResponse(String.format(Constants.EXCEPTION_NOT_FOUND_SIGNATORY, id), HttpStatus.NOT_FOUND));
    }
}
