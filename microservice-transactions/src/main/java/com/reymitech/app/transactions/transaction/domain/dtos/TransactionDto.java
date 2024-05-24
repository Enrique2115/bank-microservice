package com.reymitech.app.transactions.transaction.domain.dtos;

import lombok.Data;

@Data
public class TransactionDto {
    private String id;
    private String description;
    private double amount;
    private String accountId;
}
