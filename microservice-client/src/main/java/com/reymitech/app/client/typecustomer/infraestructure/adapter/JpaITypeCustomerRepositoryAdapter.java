package com.reymitech.app.client.typecustomer.infraestructure.adapter;

import com.reymitech.app.client.typecustomer.domain.dtos.CreateCustomerType;
import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import com.reymitech.app.client.typecustomer.domain.port.out.ITypeCustomerRepositoryPort;
import com.reymitech.app.client.utils.exceptions.GenericErrorResponse;
import com.reymitech.app.client.utils.exceptions.NotFoundException;
import com.reymitech.app.client.typecustomer.infraestructure.repository.JpaTypeCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * JpaITypeCustomerRepositoryAdapter es una clase adaptadora que implementa la interfaz ITypeCustomerRepositoryPort.
 * Proporciona métodos para crear un nuevo tipo de cliente y recuperar una lista de todos los tipos de clientes.
 *
 * @author Luis Morocho
 */
@Component
@RequiredArgsConstructor
public class JpaITypeCustomerRepositoryAdapter implements ITypeCustomerRepositoryPort {

    private final JpaTypeCustomerRepository jpaTypeCustomerRepository;

    @Override
    public TypeCustomer createCustomerType(CreateCustomerType request) {
        // Validation if exists
        jpaTypeCustomerRepository.findByName(request.getName()).ifPresent(
                typeCustomer -> { throw new GenericErrorResponse("TypeCustomer already exists", HttpStatus.CONFLICT); }
        );

        TypeCustomer newtypeCustomer = TypeCustomer.builder().name(request.getName()).build();
        return jpaTypeCustomerRepository.save(newtypeCustomer);
    }

    @Override
    public List<TypeCustomer> getAllCustomerTypes() {
        return (List<TypeCustomer>) jpaTypeCustomerRepository.findAll();
    }

    protected TypeCustomer findTypeCustomerByName(String name) {
        return jpaTypeCustomerRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("TypeCustomer not found")
        );
    }

}
