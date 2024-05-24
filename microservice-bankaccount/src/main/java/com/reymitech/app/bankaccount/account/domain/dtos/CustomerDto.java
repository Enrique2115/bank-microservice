package com.reymitech.app.bankaccount.account.domain.dtos;

import lombok.Data;

@Data
public class CustomerDto {
    private String id;
    private String username;
    private String email;
    private TypeCustomerDto typeCustomer;
}
