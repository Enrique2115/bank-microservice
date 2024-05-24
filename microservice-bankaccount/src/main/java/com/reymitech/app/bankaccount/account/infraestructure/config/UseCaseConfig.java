package com.reymitech.app.bankaccount.account.infraestructure.config;

import com.reymitech.app.bankaccount.account.application.client.ICustomerServiceClient;
import com.reymitech.app.bankaccount.account.application.service.AccountValidationService;
import com.reymitech.app.bankaccount.account.application.usecases.*;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import com.reymitech.app.bankaccount.account.domain.port.ICreditCardNumberGenerator;
import com.reymitech.app.bankaccount.account.domain.port.ISignatoryRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreatePersonalAccountUseCase createPersonalAccountUseCase(
            IAccountRepositoryPort accountRepository,
            ICustomerServiceClient ICustomerServiceClient,
            ICreditCardNumberGenerator ICreditCardNumberGenerator,
            AccountValidationService accountValidationService
    ) {
        return new CreatePersonalAccountUseCase(accountRepository, ICustomerServiceClient,
                ICreditCardNumberGenerator, accountValidationService);
    }

    @Bean
    public CreateBusinessAccountUseCase createBusinessAccountUseCase(
            IAccountRepositoryPort accountRepository,
            ISignatoryRepositoryPort signatoryRepositoryPort,
            ICustomerServiceClient customerServiceClient,
            ICreditCardNumberGenerator ICreditCardNumberGenerator,
            AccountValidationService accountValidationService
    ) {
        return new CreateBusinessAccountUseCase(accountRepository, signatoryRepositoryPort, customerServiceClient,
                ICreditCardNumberGenerator, accountValidationService);
    }

    @Bean
    public FindAccountByIdUseCase findAccountByIdUseCase(IAccountRepositoryPort accountRepository) {
        return new FindAccountByIdUseCase(accountRepository);
    }

    @Bean
    public FindAllAccountsUseCase findAllAccountsUseCase(IAccountRepositoryPort accountRepository) {
        return new FindAllAccountsUseCase(accountRepository);
    }

    @Bean
    public FindAccountByCustomerIdUseCase findAccountByCustomerIdUseCase(IAccountRepositoryPort accountRepository) {
        return new FindAccountByCustomerIdUseCase(accountRepository);
    }

    @Bean
    public UpdateAccountBalanceUseCase updateAccountBalanceUseCase(IAccountRepositoryPort accountRepository) {
        return new UpdateAccountBalanceUseCase(accountRepository);
    }
}
