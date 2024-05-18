package com.reymitech.app.client.customer.application.service;

import com.reymitech.app.client.customer.domain.dto.CreateCustomerDTO;
import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.customer.domain.port.ICustomerRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{

    private final ICustomerRepositoryPort customerRepositoryPort;

    @Override
    public Customer createCustomer(CreateCustomerDTO createCustomerDTO) {

        return customerRepositoryPort.createCustomer(createCustomerDTO);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepositoryPort.getAllCustomer();
    }
}
