package com.reymitech.app.bankaccount.usecase;

import com.reymitech.app.bankaccount.account.application.usecases.FindAllAccountsUseCase;
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

class FindAllAccountsUseCaseTest {

    @Mock
    private IAccountRepositoryPort accountRepository;

    @InjectMocks
    private FindAllAccountsUseCase findAllAccountsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldReturnAllAccounts() {
        // Arrange
        List<Account> accounts = Arrays.asList(new Account(), new Account());

        when(accountRepository.findAllAccounts()).thenReturn(accounts);

        // Act
        List<Account> foundAccounts = findAllAccountsUseCase.execute();

        // Assert
        assertNotNull(foundAccounts);
        assertEquals(2, foundAccounts.size());
    }
}