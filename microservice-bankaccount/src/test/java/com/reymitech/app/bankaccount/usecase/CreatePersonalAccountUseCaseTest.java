package com.reymitech.app.bankaccount.usecase;

import com.reymitech.app.bankaccount.account.application.client.ICustomerServiceClient;
import com.reymitech.app.bankaccount.account.application.service.AccountValidationService;
import com.reymitech.app.bankaccount.account.application.usecases.CreatePersonalAccountUseCase;
import com.reymitech.app.bankaccount.account.domain.dtos.CustomerDto;
import com.reymitech.app.bankaccount.account.domain.dtos.TypeCustomerDto;
import com.reymitech.app.bankaccount.account.domain.enums.CustomerType;
import com.reymitech.app.bankaccount.account.domain.models.Account;
import com.reymitech.app.bankaccount.account.domain.port.IAccountRepositoryPort;
import com.reymitech.app.bankaccount.account.domain.port.ICreditCardNumberGenerator;
import com.reymitech.app.bankaccount.account.infraestructure.request.CreatePersonalAccountRequest;
import com.reymitech.app.bankaccount.utils.enums.Active;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreatePersonalAccountUseCaseTest {

    @Mock
    private IAccountRepositoryPort accountRepository;

    @Mock
    private ICustomerServiceClient customerServiceClient;

    @Mock
    private ICreditCardNumberGenerator creditCardNumberGenerator;

    @Mock
    private AccountValidationService accountValidationService;

    @InjectMocks
    private CreatePersonalAccountUseCase createPersonalAccountUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_shouldCreatePersonalAccount_whenValid() {
        // Arrange
        String customerId = "123";
        CreatePersonalAccountRequest request = new CreatePersonalAccountRequest();
        request.setNameAccount("Checking Account");
        request.setTypeAccount("checking");
        request.setBalance(1000.0);

        TypeCustomerDto typeCustomer = new TypeCustomerDto();
        typeCustomer.setName("PERSONAL");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setUsername("John Doe");
        customerDto.setTypeCustomer(typeCustomer);
        customerDto.setEmail("johndoe@gmail.com");
        when(customerServiceClient.getCustomerByDocument(customerId)).thenReturn(customerDto);

        when(accountRepository.findAccountByCustomerId(customerId)).thenReturn(Collections.emptyList());
        when(accountValidationService.validatePersonalAccount(customerDto, Collections.emptyList())).thenReturn(true);
        when(creditCardNumberGenerator.generate("295963", 16)).thenReturn("2959631234567890");

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);

        // Account created
        Account createdAccount = createPersonalAccountUseCase.execute(customerId, request);

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
        CreatePersonalAccountRequest request = new CreatePersonalAccountRequest();
        request.setNameAccount("Checking Account");
        request.setTypeAccount("checking");
        request.setBalance(1000.0);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setUsername("John Doe");
        customerDto.setEmail("johndoe@gmail.com");

        when(customerServiceClient.getCustomerByDocument(customerId)).thenReturn(customerDto);

        List<Account> existingAccounts = Collections.singletonList(new Account());
        when(accountRepository.findAccountByCustomerId(customerId)).thenReturn(existingAccounts);
        when(accountValidationService.validatePersonalAccount(customerDto, existingAccounts)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createPersonalAccountUseCase.execute(customerId, request);
        });

        assertEquals("Validacion de la cuenta no se pudo realizar", exception.getMessage());
    }
}
