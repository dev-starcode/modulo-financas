package com.StraCode.finance.services;

import com.StraCode.finance.adapters.factories.AccountReceivablesFactory;
import com.StraCode.finance.domain.model.AccountReceivables;
import com.StraCode.finance.domain.repositories.AccountReceivablesRepo;
import com.StraCode.finance.dto.AccountReceivablesDto;
import com.StraCode.finance.exceptions.AccountNotFoundException;
import com.StraCode.finance.exceptions.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountReceivablesServiceTest {

    @Mock
    private AccountReceivablesRepo receivablesRepo;

    @InjectMocks
    private AccountReceivablesService receivablesService;

    private AccountReceivablesDto dtoNegativeAmount;
    private AccountReceivablesDto dtoPositiveAmount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        dtoNegativeAmount = new AccountReceivablesDto(
                UUID.randomUUID(), "Description", new BigDecimal("-100.00"),
                LocalDateTime.now(), "Status", LocalDateTime.now(),
                "ClientId", "CategoryId", "PaymentMethodId"
        );

        dtoPositiveAmount = new AccountReceivablesDto(
                UUID.randomUUID(), "Description", new BigDecimal("100.00"),
                LocalDateTime.now(),"Status", LocalDateTime.now(),
                "ClientId", "CategoryId", "PaymentMethodId"
        );
    }

    @Test
    @DisplayName("Should throw NegativeAmountException when amount is negative")
    void createAccountReceivables_WithNegativeAmount_ShouldThrowException() {
        assertThrows(NegativeAmountException.class,
                () -> receivablesService.createAccountReceivables(dtoNegativeAmount));
    }

    @Test
    @DisplayName("Should create account receivables successfully when amount is positive")
    void createAccountReceivables_WithPositiveAmount_ShouldSucceed() {
        AccountReceivables receivable = AccountReceivablesFactory.toAccountReceivables(dtoPositiveAmount);
        when(receivablesRepo.save(receivable)).thenReturn(receivable);

        AccountReceivablesDto result = receivablesService.createAccountReceivables(dtoPositiveAmount);

        assertEquals(dtoPositiveAmount, result);
    }

    @Test
    @DisplayName("Should return all account receivables successfully")
    void findAll_ShouldReturnAllAccounts() {
        List<AccountReceivables> list = List.of(
                new AccountReceivables("Description", new BigDecimal("100.00"), LocalDateTime.now(),
                        "Status", "Client1", "Category1", LocalDateTime.now(), "Payment1"),
                new AccountReceivables("Description", new BigDecimal("200.00"), LocalDateTime.now(),
                        "Status", "Client2", "Category2", LocalDateTime.now(), "Payment2")
        );

        when(receivablesRepo.findAll()).thenReturn(list);

        List<AccountReceivablesDto> result = receivablesService.findAll();

        assertEquals(2, result.size());
        assertEquals(list.get(0).getAmount(), result.get(0).getAmount());
        assertEquals(list.get(1).getAmount(), result.get(1).getAmount());
    }

    @Test
    @DisplayName("Should throw NegativeAmountException on update with negative amount")
    void updateAccountReceivables_WithNegativeAmount_ShouldThrowException() {
        UUID id = UUID.randomUUID();
        assertThrows(NegativeAmountException.class,
                () -> receivablesService.updateAccountReceivables(id, dtoNegativeAmount));
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when updating with invalid ID")
    void updateAccountReceivables_WithInvalidId_ShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(receivablesRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> receivablesService.updateAccountReceivables(id, dtoPositiveAmount));
    }

    @Test
    @DisplayName("Should update account receivables successfully with valid ID")
    void updateAccountReceivables_WithValidId_ShouldSucceed() {
        UUID id = dtoPositiveAmount.getAccountReceivablesID();
        AccountReceivables existing = new AccountReceivables(
                "Old", new BigDecimal("50.00"), LocalDateTime.now(),
                "Old", "OldClient", "OldCat", LocalDateTime.now(), "OldPay"
        );
        existing.setAccountReceivablesId(id);

        when(receivablesRepo.findById(id)).thenReturn(Optional.of(existing));
        when(receivablesRepo.save(any(AccountReceivables.class))).thenReturn(existing);

        AccountReceivablesDto result = receivablesService.updateAccountReceivables(id, dtoPositiveAmount);

        assertEquals(dtoPositiveAmount.getDescription(), result.getDescription());
        assertEquals(dtoPositiveAmount.getAmount(), result.getAmount());
    }

    @Test
    @DisplayName("Should delete account receivables successfully when ID is valid")
    void deleteAccountReceivables_WithValidId_ShouldSucceed() {
        UUID id = UUID.randomUUID();
        AccountReceivables receivable = new AccountReceivables(
                "Description", new BigDecimal("100.00"), LocalDateTime.now(),
                "Status", "Client", "Category", LocalDateTime.now(), "Payment"
        );
        receivable.setAccountReceivablesId(id);

        when(receivablesRepo.findById(id)).thenReturn(Optional.of(receivable));
        doNothing().when(receivablesRepo).delete(receivable);

        assertDoesNotThrow(() -> receivablesService.deleteAccountReceivable(id));
        verify(receivablesRepo, times(1)).delete(receivable);
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when deleting with invalid ID")
    void deleteAccountReceivables_WithInvalidId_ShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(receivablesRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> receivablesService.deleteAccountReceivable(id));
    }
}
