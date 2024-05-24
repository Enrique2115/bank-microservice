package com.reymitech.app.client.customer.domain.dto;

import com.reymitech.app.client.customer.domain.models.CustomerDetails;
import lombok.Data;

@Data
public class CustomerDTO {

    private String id;
    private String username;
    private String email;
    private CustomerDetails customerDetails;
    private TypeCustomerDTO typeCustomer;
}
