package com.reymitech.app.bankaccount.account.domain.dtos;

import lombok.Data;

@Data
public class AccountDto {

    private String id;
    private String accountName;
    private String accountNumber;
    private String accountBalance;
    private String customerId;
    private String accountType;
}
