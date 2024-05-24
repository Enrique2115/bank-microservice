package com.reymitech.app.client.customer.application.service;

import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.customer.infraestructure.request.CustomerRequest;
import com.reymitech.app.client.customer.infraestructure.request.CustomerUpdateRequest;

import java.util.List;

public interface ICustomerService {

    Customer createCustomer(CustomerRequest customerRequest);

    List<Customer> getAllCustomer();

    Customer getCustomerById(String id);

    Customer getCustomerDocument(String document);

    Customer updateCustomer(String id, CustomerUpdateRequest updateCustomerRequest);

    void deleteCustomer(String id);
}
