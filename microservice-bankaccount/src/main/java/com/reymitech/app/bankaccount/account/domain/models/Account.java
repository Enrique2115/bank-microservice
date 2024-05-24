package com.reymitech.app.bankaccount.account.domain.models;

import com.reymitech.app.bankaccount.account.domain.enums.AccountType;
import com.reymitech.app.bankaccount.utils.enums.Active;
import com.reymitech.app.bankaccount.utils.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Account extends BaseEntity {

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "account_balance", nullable = false)
    private double accountBalance;

    @Column(name = "manteniment_fee", nullable = false)
    private double mantenimentFee;

    @Column(name = "next_payment_manteniment_date", nullable = false)
    private Date nextPaymentMantenimentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "active", nullable = false)
    private Active active;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_signatory",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "signatory_id", referencedColumnName = "id")
    )
    private List<Signatory> signatory;
}
