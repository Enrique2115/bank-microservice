package com.reymitech.app.client.typecustomer.domain.port.in;

import com.reymitech.app.client.typecustomer.domain.dtos.CreateCustomerType;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;

/**
 * Interface for CreateTypeCustomerUseCase used to create a new customer type.
 */
public interface ICreateTypeCustomerUseCase {
    /**
     * Creates a new TypeCustomerDto based on the provided CreateCustomerType.
     *
     * @param  createCustomerType  the CreateCustomerType object used to create the TypeCustomerDto
     * @return                     the newly created TypeCustomerDto
     */
    TypeCustomer createCustomerType(CreateCustomerType createCustomerType);
}
