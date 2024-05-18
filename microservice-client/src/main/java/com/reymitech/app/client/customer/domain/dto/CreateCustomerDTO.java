package com.reymitech.app.client.customer.domain.dto;

import lombok.Data;

@Data
public class CreateCustomerDTO {

    private String name;
    private String lastname;
    private String document;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String typeCustomer;
}
