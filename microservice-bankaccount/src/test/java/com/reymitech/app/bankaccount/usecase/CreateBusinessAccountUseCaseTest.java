package com.reymitech.app.bankaccount.usecase;

import com.reymitech.app.bankaccount.account.application.client.ICustomerServiceClient;
import com.reymitech.app.bankaccount.account.application.usecases.CreateBusinessAccountUseCase;
import com.reymitech.app.bankaccount.account.domain.dtos.CustomerDto;
import com.reymitech.app.bankaccount.account.domain.dtos.TypeCustomerDto;
import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import com.reymitech.app.bankaccount.account.domain.port.IAccountValidationService;
import com.reymitech.app.bankaccount.account.domain.port.ICreditCardNumberGenerator;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreateBusinessAccountRequest;

import com.reymitech.app.bankaccount.utils.enums.Active;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateBusinessAccountUseCaseTest {

    @Mock
    private IAccountRepositoryPort accountRepository;

    @Mock
    private ICustomerServiceClient customerServiceClient;

    @Mock
    private ICreditCardNumberGenerator creditCardNumberGenerator;

    @Mock
    private IAccountValidationService accountValidationService;

    @InjectMocks
    private CreateBusinessAccountUseCase createBusinessAccountUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldCreateBusinessAccount_whenValid() {
        // Arrange
        String customerId = "123";
        CreateBusinessAccountRequest request = new CreateBusinessAccountRequest();
        request.setNameAccount("Business Account");
        request.setTypeAccount("checking");
        request.setBalance(5000.0);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setUsername("Acme Corp");
        customerDto.setEmail("acmecorp@gmail.com");
        customerDto.setTypeCustomer(new TypeCustomerDto());

        when(customerServiceClient.getCustomerByDocument(customerId)).thenReturn(customerDto);
        when(accountValidationService.validateBusinessAccount(customerDto, request.getTypeAccount())).thenReturn(true);
        when(creditCardNumberGenerator.generate("295963", 16)).thenReturn("2959639876543210");

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);

        // Act
        Account createdAccount = createBusinessAccountUseCase.execute(customerId, request);

        // Assert
        verify(accountRepository, times(1)).save(accountCaptor.capture());
        Account savedAccount = accountCaptor.getValue();

        assertNotNull(createdAccount);
        assertEquals(savedAccount.getAccountNumber(), createdAccount.getAccountNumber());
        assertEquals(savedAccount.getAccountName(), createdAccount.getAccountName());
        assertEquals(savedAccount.getAccountType(), createdAccount.getAccountType());
        assertEquals(savedAccount.getAccountBalance(), createdAccount.getAccountBalance());
        assertEquals(savedAccount.getCustomerId(), createdAccount.getCustomerId());
        assertEquals(savedAccount.getActive(), Active.ACTIVE);
    }

    @Test
    void execute_shouldThrowException_whenValidationFails() {
        // Arrange
        String customerId = "123";
        CreateBusinessAccountRequest request = new CreateBusinessAccountRequest();
        request.setNameAccount("Business Account");
        request.setTypeAccount("checking");
        request.setBalance(5000.0);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setUsername("Acme Corp");
        customerDto.setEmail("acmecorp@gmail.com");
        customerDto.setTypeCustomer(new TypeCustomerDto());
        when(customerServiceClient.getCustomerByDocument(customerId)).thenReturn(customerDto);

        when(accountValidationService.validateBusinessAccount(customerDto, request.getTypeAccount())).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createBusinessAccountUseCase.execute(customerId, request);
        });

        assertEquals("Validation failed", exception.getMessage());
    }
}