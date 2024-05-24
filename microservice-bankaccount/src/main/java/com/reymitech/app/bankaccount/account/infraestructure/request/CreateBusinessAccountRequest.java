package com.reymitech.app.bankaccount.account.infraestructure.request;

import com.reymitech.app.bankaccount.account.domain.models.Signatory;
import lombok.Data;

import java.util.List;

@Data
public class CreateBusinessAccountRequest {

    private String nameAccount;
    private String typeAccount;
    private double balance;
    private List<Signatory> signatories;

}
