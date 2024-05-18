package com.reymitech.app.client.customer.infraestructure.adapter;

import com.reymitech.app.client.customer.domain.dto.CreateCustomerDTO;
import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.customer.domain.models.CustomerDetails;
import com.reymitech.app.client.customer.domain.port.ICustomerRepositoryPort;
import com.reymitech.app.client.customer.infraestructure.repository.JpaCustomerRepository;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import com.reymitech.app.client.typecustomer.infraestructure.repository.JpaTypeCustomerRepository;
import com.reymitech.app.client.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaCustomerRepositoryAdapter implements ICustomerRepositoryPort {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaTypeCustomerRepository jpaTypeCustomerRepository;

    @Override
    public Customer createCustomer(CreateCustomerDTO createCustomerDTO) {

        jpaCustomerRepository.findCustomerByCustomerDetailsDocument(createCustomerDTO.getDocument()).ifPresent(
                customer -> { throw new ResponseStatusException(HttpStatus.CONFLICT, "Customer already exists"); }
        );

        var typeCustomer = findTypeCustomerByName(createCustomerDTO.getTypeCustomer());

        CustomerDetails customerDetails = CustomerDetails.builder()
                .firstName(createCustomerDTO.getName())
                .lastName(createCustomerDTO.getLastname())
                .phone(createCustomerDTO.getPhone())
                .address(createCustomerDTO.getAddress())
                .document(createCustomerDTO.getDocument())
                .build();

        Customer customer = Customer.builder()
                .username(createCustomerDTO.getUsername())
                .password(createCustomerDTO.getPassword())
                .email(createCustomerDTO.getEmail())
                .customerDetails(customerDetails)
                .typeCustomer(typeCustomer)
                .build();

        return jpaCustomerRepository.save(customer);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return jpaCustomerRepository.findAll();
    }

    protected TypeCustomer findTypeCustomerByName(String id) {
        return jpaTypeCustomerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("TypeCustomer not found")
        );
    }
}
