package com.reymitech.app.bankaccount.account.infraestructure.request;

import lombok.Data;

@Data
public class UpdateAccountBalanceRequest {

    private final String numAccount;
    private final double balance;
}
