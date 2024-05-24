package com.reymitech.app.client.customer.infraestructure.controller;

import com.reymitech.app.client.customer.application.service.ICustomerService;
import com.reymitech.app.client.customer.domain.dto.CustomerDTO;
import com.reymitech.app.client.customer.infraestructure.request.CustomerRequest;
import com.reymitech.app.client.customer.infraestructure.request.CustomerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return ResponseEntity.ok(
                customerService.getAllCustomer()
                        .stream().map(
                                customer -> modelMapper.map(customer, CustomerDTO.class)
                        ).collect(Collectors.toList())
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerDTO> create(@Valid @RequestBody final CustomerRequest createCustomerRequest) {
        return ResponseEntity.ok(
                modelMapper.map(customerService.createCustomer(createCustomerRequest), CustomerDTO.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable final String id) {
        return ResponseEntity.ok(
                modelMapper.map(customerService.getCustomerById(id), CustomerDTO.class)
        );
    }

    @GetMapping("/document")
    public ResponseEntity<CustomerDTO> getDocument(@RequestParam final String document) {
        return ResponseEntity.ok(
                modelMapper.map(customerService.getCustomerDocument(document), CustomerDTO.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable final String id, @RequestBody final CustomerUpdateRequest updateCustomerRequest) {
        return ResponseEntity.ok(
                modelMapper.map(customerService.updateCustomer(id, updateCustomerRequest), CustomerDTO.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
