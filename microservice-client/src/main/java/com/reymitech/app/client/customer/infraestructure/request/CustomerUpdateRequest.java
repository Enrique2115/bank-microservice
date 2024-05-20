package com.reymitech.app.client.customer.infraestructure.request;

import com.reymitech.app.client.customer.domain.models.CustomerDetails;
import lombok.Data;

@Data
public class CustomerUpdateRequest {

    private String username;
    private String password;
    private String email;
    private CustomerDetails customerDetails;

}
