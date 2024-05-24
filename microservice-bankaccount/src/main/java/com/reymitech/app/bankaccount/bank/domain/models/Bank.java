package com.reymitech.app.bankaccount.bank.domain.models;

import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.utils.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "bank")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bank extends BaseEntity {

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "bank_address", nullable = false)
    private String bankAddress;

    @Column(name = "savings_accounts_limit", nullable = false)
    private double savingsAccountsLimit;

    @Column(name = "checking_accounts_limit", nullable = true)
    private double checkingAccountsLimit;

    @Column(name = "fixed_term_accounts_limit", nullable = false)
    private double fixedTermAccountsLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "active", nullable = false)
    private Active active;

    public void applyBankingFee(double amount) {

    }
}
