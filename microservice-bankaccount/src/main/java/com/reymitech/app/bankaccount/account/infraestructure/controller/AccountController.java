package com.reymitech.app.bankaccount.account.infraestructure.controller;

import com.reymitech.app.bankaccount.account.application.client.ICustomerServiceClient;
import com.reymitech.app.bankaccount.account.application.usecases.*;
import com.reymitech.app.bankaccount.account.domain.dtos.AccountDto;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreateBusinessAccountRequest;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreatePersonalAccountRequest;
import com.reymitech.app.bankaccount.account.infraestructure.request.UpdateAccountBalanceRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CreatePersonalAccountUseCase createPersonalAccountUseCase;
    private final CreateBusinessAccountUseCase createBusinessAccountUseCase;
    private final FindAccountByIdUseCase findAccountByIdUseCase;
    private final FindAllAccountsUseCase findAllAccountsUseCase;
    private final FindAccountByCustomerIdUseCase findAccountByCustomerIdUseCase;
    private final UpdateAccountBalanceUseCase updateAccountBalanceUseCase;
    private final ModelMapper modelMapper = new ModelMapper();

    private final ICustomerServiceClient customerServiceClient;

    @GetMapping("/balance")
    public String getBalance() {
        return "100";
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable final String id) {
        return ResponseEntity.ok(
                modelMapper.map(findAccountByIdUseCase.execute(id), AccountDto.class)
        );
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(
                findAllAccountsUseCase.execute()
                        .stream().map(
                                customer -> modelMapper.map(customer, AccountDto.class)
                        ).collect(Collectors.toList())
        );
    }

    @GetMapping("/{customerId}/customer")
    public ResponseEntity<List<AccountDto>> getAccountDetails(@PathVariable final String customerId) {
        return ResponseEntity.ok(
                findAccountByCustomerIdUseCase.execute(customerId)
                        .stream().map(
                                customer -> modelMapper.map(customer, AccountDto.class)
                        ).collect(Collectors.toList())
        );
    }

    @PostMapping("/personal")
    public ResponseEntity<AccountDto> createPersonalAccount(
            @RequestParam final String document, @RequestBody final CreatePersonalAccountRequest request
    ) {
        return ResponseEntity.ok(
                modelMapper.map(createPersonalAccountUseCase.execute(document, request), AccountDto.class)
        );
    }

    @PostMapping("/business")
    public ResponseEntity<AccountDto> createBusinessAccount(
            @RequestParam final String document, @RequestBody final CreateBusinessAccountRequest request
    ) {
        return ResponseEntity.ok(
                modelMapper.map(createBusinessAccountUseCase.execute(document, request), AccountDto.class)
        );
    }

    @PatchMapping("/balance")
    public ResponseEntity<AccountDto> updateAccountBalance(
            @RequestBody final UpdateAccountBalanceRequest request
    ) {
        return ResponseEntity.ok(
                modelMapper.map(updateAccountBalanceUseCase.execute(request), AccountDto.class)
        );
    }

}
