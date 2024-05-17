package com.reymitech.app.client.typecustomer.domain.port.out;

import com.reymitech.app.client.typecustomer.domain.dtos.CreateCustomerType;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;

import java.util.List;

public interface ITypeCustomerRepositoryPort {

    /**
     * Creates a new customer type using the provided CreateCustomerType object.
     *
     * @param  createCustomerType  the CreateCustomerType object containing the details of the customer type to be created
     * @return                     the created TypeCustomerDto object
     */
    TypeCustomer createCustomerType(final CreateCustomerType createCustomerType);

    /**
     * Retrieves a list of all customer types.
     *
     * @return a list of TypeCustomer objects representing all customer types
     */
    List<TypeCustomer> getAllCustomerTypes();
}
