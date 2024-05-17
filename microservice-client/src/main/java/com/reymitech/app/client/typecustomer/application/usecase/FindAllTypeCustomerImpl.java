package com.reymitech.app.client.typecustomer.application.usecase;

import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import com.reymitech.app.client.typecustomer.domain.port.in.IFindAllTypeCustomerUseCase;
import com.reymitech.app.client.typecustomer.domain.port.out.ITypeCustomerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllTypeCustomerImpl implements IFindAllTypeCustomerUseCase {

    private final ITypeCustomerRepositoryPort typeCustomerRepositoryPort;

    @Override
    public List<TypeCustomer> getAllCustomerTypes() {
        return typeCustomerRepositoryPort.getAllCustomerTypes();
    }
}
