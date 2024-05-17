package com.reymitech.app.client.typecustomer.application.service;

import com.reymitech.app.client.typecustomer.domain.dtos.CreateCustomerType;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;

import java.util.List;

/**
 * Interface for TypeCustomerService used to access the customer type service.
 */
public interface ITypeCustomerService {

    /**
     * Creates a new customer type by mapping the provided CreateCustomerType object to a TypeCustomerDto object using ModelMapper.
     *
     * @param createCustomerType the CreateCustomerType object containing the details of the customer type to be created
     * @return a ResponseEntity containing the created TypeCustomerDto object, or an error if the creation fails
     */
    TypeCustomer createCustomerType(CreateCustomerType createCustomerType);

    /**
     * Retrieves a list of all customer types.
     *
     * @return a list of TypeCustomer objects representing all customer types
     */
    List<TypeCustomer> getAllCustomerTypes();
}
