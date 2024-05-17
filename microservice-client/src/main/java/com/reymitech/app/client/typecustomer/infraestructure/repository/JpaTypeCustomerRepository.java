package com.reymitech.app.client.typecustomer.infraestructure.repository;

import com.reymitech.app.client.typecustomer.domain.models.TypeCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTypeCustomerRepository extends CrudRepository<TypeCustomer, String> {

    Optional<TypeCustomer> findByName(String name);
}
