package com.reymitech.app.transactions.transaction.application.client;

import com.reymitech.app.transactions.transaction.domain.dtos.AccountDto;
import com.reymitech.app.transactions.transaction.domain.dtos.BankDto;
import com.reymitech.app.transactions.transaction.infraestructure.request.UpdateAccountBalanceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-bankaccount")
public interface IBankAccountService {

    @GetMapping("/api/v1/accounts/{id}")
    AccountDto getAccountById(@PathVariable final String id);

    @RequestMapping(value = "/api/v1/accounts/balance", method = RequestMethod.PATCH)
    AccountDto UpdateAccountBalanceRequest(@RequestBody final UpdateAccountBalanceRequest request);

    @GetMapping("/api/v1/bank/get")
    BankDto getBank();

}
