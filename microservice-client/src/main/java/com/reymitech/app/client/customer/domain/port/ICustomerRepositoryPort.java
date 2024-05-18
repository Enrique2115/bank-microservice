package com.reymitech.app.client.customer.domain.port;

import com.reymitech.app.client.customer.domain.dto.CreateCustomerDTO;
import com.reymitech.app.client.customer.domain.models.Customer;

import java.util.List;

public interface ICustomerRepositoryPort {

    Customer createCustomer(CreateCustomerDTO createCustomerDTO);

    //void updateCustomer(CreateCustomerDTO createCustomerDTO);

    List<Customer> getAllCustomer();
}
