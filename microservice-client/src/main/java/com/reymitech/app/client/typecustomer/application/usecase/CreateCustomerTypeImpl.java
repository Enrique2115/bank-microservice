package com.reymitech.app.client.typecustomer.application.usecase;

import com.reymitech.app.client.typecustomer.domain.dtos.CreateCustomerType;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import com.reymitech.app.client.typecustomer.domain.port.in.ICreateTypeCustomerUseCase;
import com.reymitech.app.client.typecustomer.domain.port.out.ITypeCustomerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCustomerTypeImpl implements ICreateTypeCustomerUseCase {

    private final ITypeCustomerRepositoryPort typeCustomerRepositoryPort;

    @Override
    public TypeCustomer createCustomerType(final CreateCustomerType createCustomerType) {
        return typeCustomerRepositoryPort.createCustomerType(createCustomerType);
    }
}
