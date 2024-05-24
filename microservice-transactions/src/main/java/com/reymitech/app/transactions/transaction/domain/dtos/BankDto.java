package com.reymitech.app.transactions.transaction.domain.dtos;

import lombok.Data;

@Data
public class BankDto {
    private String id;
    private String bankName;
    private String bankAddress;
    private int savingsAccountsLimit;
    private int checkingAccountsLimit;
    private int fixedTermAccountsLimit;
}
