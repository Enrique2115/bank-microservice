package com.reymitech.app.client.customer.domain.port;

import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.customer.infraestructure.request.CustomerRequest;
import com.reymitech.app.client.customer.infraestructure.request.CustomerUpdateRequest;

import java.util.List;

public interface ICustomerRepositoryPort {

    Customer createCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomer();

    Customer getCustomerById(String id);

    Customer updateCustomer(String id, CustomerUpdateRequest updateCustomerRequest);

    void deleteCustomer(String id);
}
