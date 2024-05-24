package com.reymitech.app.bankaccount.account.domain.models;

import com.reymitech.app.bankaccount.utils.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity(name = "signatory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Signatory extends BaseEntity {

    private String name;
    private String email;
}
