package com.reymitech.app.client.customer.infraestructure.adapter;

import com.reymitech.app.client.customer.domain.enums.Active;
import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.customer.domain.models.CustomerDetails;
import com.reymitech.app.client.customer.domain.port.ICustomerRepositoryPort;
import com.reymitech.app.client.customer.infraestructure.repository.JpaCustomerRepository;
import com.reymitech.app.client.customer.infraestructure.request.CustomerRequest;
import com.reymitech.app.client.customer.infraestructure.request.CustomerUpdateRequest;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import com.reymitech.app.client.typecustomer.infraestructure.repository.JpaTypeCustomerRepository;
import com.reymitech.app.client.utils.exceptions.GenericErrorResponse;
import com.reymitech.app.client.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaCustomerRepositoryAdapter implements ICustomerRepositoryPort {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaTypeCustomerRepository jpaTypeCustomerRepository;
    private final ModelMapper modelMapper;

    @Override
    public Customer createCustomer(CustomerRequest createCustomerRequest) {

        jpaCustomerRepository.findCustomerByCustomerDetailsDocument(createCustomerRequest.getDocument()).ifPresent(
                customer -> {
                    throw new GenericErrorResponse("Cliente ya existe", HttpStatus.CONFLICT);
                }
        );

        var typeCustomer = findTypeCustomerByName(createCustomerRequest.getTypeCustomer());

        CustomerDetails customerDetails = CustomerDetails.builder()
                .firstName(createCustomerRequest.getName())
                .lastName(createCustomerRequest.getLastname())
                .phone(createCustomerRequest.getPhone())
                .address(createCustomerRequest.getAddress())
                .document(createCustomerRequest.getDocument())
                .build();

        Customer customer = Customer.builder()
                .username(createCustomerRequest.getUsername())
                .password(createCustomerRequest.getPassword())
                .email(createCustomerRequest.getEmail())
                .customerDetails(customerDetails)
                .typeCustomer(typeCustomer)
                .active(Active.ACTIVE)
                .build();

        return jpaCustomerRepository.save(customer);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return jpaCustomerRepository.findAllByActive(Active.ACTIVE);
    }

    @Override
    public Customer getCustomerById(String id) {
        return findCustomerById(id);
    }

    @Override
    public Customer getCustomerDocument(String document) {
        return jpaCustomerRepository.findCustomerByCustomerDetailsDocument(document).orElseThrow(
                () -> new NotFoundException("Cliente no encontrado")
        );
    }

    @Override
    public Customer updateCustomer(String id, CustomerUpdateRequest request) {
        Customer toUpdate = findCustomerById(id);

        request.setCustomerDetails(updateCustomerDetails(toUpdate.getCustomerDetails(), request.getCustomerDetails()));

        return jpaCustomerRepository.save(toUpdate);
    }

    @Override
    public void deleteCustomer(String id) {
        Customer customer = findCustomerById(id);
        customer.setActive(Active.INACTIVE);
        jpaCustomerRepository.save(customer);
    }

    protected Customer findCustomerById(String id) {
        return jpaCustomerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Cliente no encontrado")
        );
    }

    private CustomerDetails updateCustomerDetails(CustomerDetails toUpdate, CustomerDetails request) {
        toUpdate = toUpdate == null ? new CustomerDetails() : toUpdate;

        modelMapper.map(request, toUpdate);

        return toUpdate;
    }

    protected TypeCustomer findTypeCustomerByName(String id) {
        return jpaTypeCustomerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("TypeCustomer not found")
        );
    }
}
