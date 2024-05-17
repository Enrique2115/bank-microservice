package com.reymitech.app.client.typecustomer.domain.port.in;

import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;

import java.util.List;

public interface IFindAllTypeCustomerUseCase {

    List<TypeCustomer> getAllCustomerTypes();
}
