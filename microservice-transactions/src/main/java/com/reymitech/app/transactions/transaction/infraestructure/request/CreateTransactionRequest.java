package com.reymitech.app.transactions.transaction.infraestructure.request;

import lombok.Data;

@Data
public class CreateTransactionRequest {
    private String description;
    private String type;
    private double amount;
    private String accountId;
}
