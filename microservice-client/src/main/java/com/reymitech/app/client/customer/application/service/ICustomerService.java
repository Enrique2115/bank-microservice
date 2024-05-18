package com.reymitech.app.client.customer.application.service;

import com.reymitech.app.client.customer.domain.dto.CreateCustomerDTO;
import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.customer.infraestructure.request.CustomerRequest;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;

import java.util.List;

public interface ICustomerService {

    Customer createCustomer(CreateCustomerDTO createCustomerDTO);

    //void updateCustomer(CreateCustomerDTO createCustomerDTO);

    List<Customer> getAllCustomer();

}
