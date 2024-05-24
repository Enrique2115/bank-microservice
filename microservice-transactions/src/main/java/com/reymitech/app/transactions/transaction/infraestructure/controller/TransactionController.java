package com.reymitech.app.transactions.transaction.infraestructure.controller;

import com.reymitech.app.transactions.transaction.application.usecase.CreateTransactionUseCase;
import com.reymitech.app.transactions.transaction.infraestructure.request.CreateTransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final CreateTransactionUseCase createTransactionUseCase;

    @PostMapping()
    public void createTransaction(@RequestBody CreateTransactionRequest request) {
        createTransactionUseCase.execute(request);
    }
}
