package com.reymitech.app.client.customer.infraestructure.controller;

import com.reymitech.app.client.customer.application.service.ICustomerService;
import com.reymitech.app.client.customer.domain.dto.CreateCustomerDTO;
import com.reymitech.app.client.customer.infraestructure.request.CustomerRequest;
import com.reymitech.app.client.typecustomer.infraestructure.request.TypeCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<List<CustomerRequest>> getAll() {
        return ResponseEntity.ok(
                customerService.getAllCustomer()
                        .stream().map(
                                customer -> modelMapper.map(customer, CustomerRequest.class)
                        ).collect(Collectors.toList())
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerRequest> create(@RequestBody final CreateCustomerDTO createCustomerDTO) {

        return ResponseEntity.ok(
                modelMapper.map(customerService.createCustomer(createCustomerDTO), CustomerRequest.class)
        );
    }
}
