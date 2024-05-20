package com.reymitech.app.client.customer.infraestructure.repository;

import com.reymitech.app.client.customer.domain.enums.Active;
import com.reymitech.app.client.customer.domain.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findCustomerByCustomerDetailsDocument(String document);

    List<Customer> findAllByActive(Active active);
}
