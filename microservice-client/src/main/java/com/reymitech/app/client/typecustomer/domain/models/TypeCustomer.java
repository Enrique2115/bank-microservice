package com.reymitech.app.client.typecustomer.domain.models;

import com.reymitech.app.client.customer.domain.models.Customer;
import com.reymitech.app.client.utils.BaseEntity;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "type_customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeCustomer extends BaseEntity {

    /** Column for the name of the type customer. */
    @Column(nullable = false, unique = true)
    private String name;

    /** Column for the list of customers. */
    @OneToMany(mappedBy = "typeCustomer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customers;
}
