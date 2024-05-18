package com.reymitech.app.client.customer.infraestructure.request;

import lombok.Data;

@Data
public class CustomerRequest {

    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String document;

}
