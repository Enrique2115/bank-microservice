package com.reymitech.app.bankaccount.bank.infraestructure.request;

import lombok.Data;

@Data
public class CreateBankRequest {

    private String bankName;
    private String bankAddress;
    private double savingsAccountsLimit;
    private double checkingAccountsLimit;
    private double fixedTermAccountsLimit;

}
