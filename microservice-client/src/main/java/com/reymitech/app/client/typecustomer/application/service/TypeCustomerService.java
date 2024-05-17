package com.reymitech.app.client.typecustomer.application.service;

import com.reymitech.app.client.typecustomer.domain.dtos.CreateCustomerType;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import com.reymitech.app.client.typecustomer.domain.port.in.ICreateTypeCustomerUseCase;
import com.reymitech.app.client.typecustomer.domain.port.in.IFindAllTypeCustomerUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * No es necesario emplear el @Service debido a que se inyecta mediante el Bean config
 * @author Luis Morocho
 */
//@Service

@RequiredArgsConstructor
public class TypeCustomerService implements ITypeCustomerService{

    private final ICreateTypeCustomerUseCase ICreateTypeCustomerUseCase;
    private final IFindAllTypeCustomerUseCase IFindAllTypeCustomerUseCase;

    @Override
    public TypeCustomer createCustomerType(final CreateCustomerType createCustomerType) {
        return ICreateTypeCustomerUseCase.createCustomerType(createCustomerType);
    }

    @Override
    public List<TypeCustomer> getAllCustomerTypes() {
        return IFindAllTypeCustomerUseCase.getAllCustomerTypes();
    }


}
