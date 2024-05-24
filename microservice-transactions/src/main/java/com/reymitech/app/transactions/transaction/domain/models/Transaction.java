package com.reymitech.app.transactions.transaction.domain.models;

import com.reymitech.app.transactions.transaction.domain.enums.TypeTransaction;
import com.reymitech.app.transactions.utils.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Transaction extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "available_balance", nullable = false)
    private double availableBalance;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "account_id", nullable = false)
    private String accountId;

}
