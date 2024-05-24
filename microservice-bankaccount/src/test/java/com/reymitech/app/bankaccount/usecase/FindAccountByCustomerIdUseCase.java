package com.reymitech.app.bankaccount.usecase;

import com.reymitech.app.bankaccount.account.application.usecases.FindAccountByCustomerIdUseCase;
import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindAccountByCustomerIdUseCaseTest {

    @Mock
    private IAccountRepositoryPort accountRepository;

    @InjectMocks
    private FindAccountByCustomerIdUseCase findAccountByCustomerIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldReturnAccountsByCustomerId() {
        // Arrange
        String customerId = "123";
        List<Account> accounts = Arrays.asList(new Account(), new Account());

        when(accountRepository.findAccountByCustomerId(customerId)).thenReturn(accounts);

        // Act
        List<Account> foundAccounts = findAccountByCustomerIdUseCase.execute(customerId);

        // Assert
        assertNotNull(foundAccounts);
        assertEquals(2, foundAccounts.size());
    }
}