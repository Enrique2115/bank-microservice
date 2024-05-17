package com.reymitech.app.client.typecustomer.infraestructure.controller;

import com.reymitech.app.client.typecustomer.application.service.ITypeCustomerService;
import com.reymitech.app.client.typecustomer.domain.dtos.CreateCustomerType;
import com.reymitech.app.client.typecustomer.infraestructure.request.TypeCustomerRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer-type")
@RequiredArgsConstructor
public class CustomerType {

    private final ITypeCustomerService typeCustomerService;
    private final ModelMapper modelMapper;

    /**
     * Creates a new customer type by mapping the provided CreateCustomerType object to a TypeCustomerDto object using ModelMapper.
     *
     * @param createCustomerType the CreateCustomerType object containing the details of the customer type to be created
     * @return a ResponseEntity containing the created TypeCustomerDto object, or an error if the creation fails
     */
    @PostMapping()
    public ResponseEntity<TypeCustomerRequest> createCustomerType(
            @Valid @RequestBody final CreateCustomerType createCustomerType
    ) {
        return ResponseEntity.ok(
                modelMapper.map(typeCustomerService.createCustomerType(createCustomerType), TypeCustomerRequest.class)
        );
    }

    /**
     * Retrieves all customer types and returns them as a list of TypeCustomerDto objects.
     *
     * @return         	A ResponseEntity containing the list of TypeCustomerDto objects, or an error if the retrieval fails.
     */
    @GetMapping()
    public ResponseEntity<List<TypeCustomerRequest>> getAllCustomerTypes() {
        return ResponseEntity.ok(
                typeCustomerService.getAllCustomerTypes()
                        .stream().map(
                                typeCustomer -> modelMapper.map(typeCustomer, TypeCustomerRequest.class)
                        ).collect(Collectors.toList())
        );
    }
}
