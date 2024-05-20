package com.reymitech.app.client.customer.application.service;

import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.customer.domain.port.ICustomerRepositoryPort;
import com.reymitech.app.client.customer.infraestructure.request.CustomerRequest;
import com.reymitech.app.client.customer.infraestructure.request.CustomerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepositoryPort customerRepositoryPort;

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepositoryPort.createCustomer(customerRequest);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepositoryPort.getAllCustomer();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepositoryPort.getCustomerById(id);
    }

    @Override
    public Customer updateCustomer(String id, CustomerUpdateRequest customerRequest) {
        return customerRepositoryPort.updateCustomer(id, customerRequest);
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepositoryPort.deleteCustomer(id);
    }
}
