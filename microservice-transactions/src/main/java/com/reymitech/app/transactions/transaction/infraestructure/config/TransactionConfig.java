package com.reymitech.app.transactions.transaction.infraestructure.config;

import com.reymitech.app.transactions.transaction.application.client.IBankAccountService;
import com.reymitech.app.transactions.transaction.application.usecase.CreateTransactionUseCase;
import com.reymitech.app.transactions.transaction.domain.port.ITransactionRepositoryPort;
import com.reymitech.app.transactions.transaction.domain.port.ITransactionValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfig {

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(
            ITransactionRepositoryPort transactionRepositoryPort,
            ITransactionValidationService transactionValidationService,
            IBankAccountService accountService
    ) {
        return new CreateTransactionUseCase(transactionRepositoryPort, transactionValidationService, accountService);
    }
}
