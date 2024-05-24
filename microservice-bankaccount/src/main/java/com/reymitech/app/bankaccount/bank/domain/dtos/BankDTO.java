package com.reymitech.app.bankaccount.bank.domain.dtos;

import lombok.Data;

@Data
public class BankDTO {

    private String id;
    private String bankName;
    private String bankAddress;
    private int savingsAccountsLimit;
    private int checkingAccountsLimit;
    private int fixedTermAccountsLimit;
}
