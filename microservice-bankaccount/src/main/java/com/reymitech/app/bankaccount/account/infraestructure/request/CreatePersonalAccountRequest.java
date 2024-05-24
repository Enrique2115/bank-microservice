package com.reymitech.app.bankaccount.account.infraestructure.request;

import lombok.Data;

@Data
public class CreatePersonalAccountRequest {

    private String nameAccount;
    private String typeAccount;
    private double balance;
}
