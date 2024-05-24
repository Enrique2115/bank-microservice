package com.reymitech.app.bankaccount.bank.infraestructure.config;

import com.reymitech.app.bankaccount.bank.application.usecase.CreateBankUseCase;
import com.reymitech.app.bankaccount.bank.application.usecase.DesactiveBankUseCase;
import com.reymitech.app.bankaccount.bank.application.usecase.FindBankUseCase;
import com.reymitech.app.bankaccount.bank.application.usecase.UpdateBankUseCase;
import com.reymitech.app.bankaccount.bank.domain.port.IBankRepositoryPort;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankUseCaseConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }

    @Bean
    public CreateBankUseCase createBankUseCase(
            IBankRepositoryPort bankRepositoryPort
    ) {
        return new CreateBankUseCase(bankRepositoryPort);
    }

    @Bean
    public FindBankUseCase findBankUseCase(
            IBankRepositoryPort bankRepositoryPort
    ) {
        return new FindBankUseCase(bankRepositoryPort);
    }

    @Bean
    public UpdateBankUseCase updateBankUseCase(
            IBankRepositoryPort bankRepositoryPort
    ) {
        return new UpdateBankUseCase(bankRepositoryPort);
    }

    @Bean
    public DesactiveBankUseCase desactiveBankUseCase(
            IBankRepositoryPort bankRepositoryPort
    ) {
        return new DesactiveBankUseCase(bankRepositoryPort);
    }
}
