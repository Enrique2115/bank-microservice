package com.reymitech.app.bankaccount.usecase;

import com.reymitech.app.bankaccount.account.application.usecases.FindAccountByIdUseCase;
import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindAccountByIdUseCaseTest {

    @Mock
    private IAccountRepositoryPort accountRepository;

    @InjectMocks
    private FindAccountByIdUseCase findAccountByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldReturnAccount_whenFound() {
        // Arrange
        String accountId = "123";
        Account account = new Account();
        account.setCustomerId(accountId);

        when(accountRepository.findAccountById(accountId));

        // Act
        Account foundAccount = findAccountByIdUseCase.execute(accountId);

        // Assert
        assertNotNull(foundAccount);
        assertEquals(accountId, foundAccount.getId());
    }

    @Test
    void execute_shouldThrowException_whenNotFound() {
        // Arrange
        String accountId = "123";

        when(accountRepository.findAccountById(accountId));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            findAccountByIdUseCase.execute(accountId);
        });

        assertEquals(String.format("Cuenta con id %s no encontrada", accountId), exception.getMessage());
    }
}