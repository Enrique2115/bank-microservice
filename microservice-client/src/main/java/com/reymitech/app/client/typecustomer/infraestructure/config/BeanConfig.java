package com.reymitech.app.client.typecustomer.infraestructure.config;

import com.reymitech.app.client.typecustomer.application.service.TypeCustomerService;
import com.reymitech.app.client.typecustomer.application.usecase.CreateCustomerTypeImpl;

import com.reymitech.app.client.typecustomer.application.usecase.FindAllTypeCustomerImpl;
import com.reymitech.app.client.typecustomer.domain.port.out.ITypeCustomerRepositoryPort;
import com.reymitech.app.client.typecustomer.infraestructure.adapter.JpaITypeCustomerRepositoryAdapter;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }

    @Bean
    public TypeCustomerService typeCustomerService(ITypeCustomerRepositoryPort typeCustomerRepositoryPort) {
        return new TypeCustomerService(
                new CreateCustomerTypeImpl(typeCustomerRepositoryPort),
                new FindAllTypeCustomerImpl(typeCustomerRepositoryPort)
        );
    }

    @Bean
    public ITypeCustomerRepositoryPort typeCustomerRepositoryPort(JpaITypeCustomerRepositoryAdapter jpaTypeCustomerRepositoryAdapter) {
        return jpaTypeCustomerRepositoryAdapter;
    }
}
