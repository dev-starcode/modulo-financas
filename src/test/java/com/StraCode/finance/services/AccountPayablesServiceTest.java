package com.StraCode.finance.services;

import com.StraCode.finance.adapters.factories.AccountPayablesFactory;
import com.StraCode.finance.domain.model.AccountPayables;
import com.StraCode.finance.domain.repositories.AccountPayablesRepo;
import com.StraCode.finance.dto.AccountPayablesDto;
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

class AccountPayablesServiceTest {

    @Mock
    private AccountPayablesRepo payableRepo;

    @InjectMocks
    private AccountPayablesService payablesService;

    private AccountPayablesDto payablesDtoNegativeAmount;

    private AccountPayablesDto payablesDtoPositiveAmount;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        payablesDtoNegativeAmount = new AccountPayablesDto(UUID.randomUUID(),
                "Description",
                new BigDecimal("-200.00"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Status",
                "999999901",
                "99999902",
                "999999903");

        payablesDtoPositiveAmount = new AccountPayablesDto(UUID.randomUUID(),
                "Description",
                new BigDecimal("200.00"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Status",
                "999999901",
                "99999902",
                "999999903");
    }
    @Test
    @DisplayName("Should throw NegativeAmountException when amount is a negative value")
    void createAccountPayable_WithNegativeAmount_ShouldThrowNegativeAmountException() {
        assertThrows(NegativeAmountException.class, () ->  payablesService.createAccountPayable(payablesDtoNegativeAmount));
    }

    @Test
    @DisplayName("Should throw NegativeAmountException when amount is a negative value")
    void updateAccountPayable_WithNegativeAmount_ShouldThrowNegativeAmountException() {
        assertThrows(NegativeAmountException.class, () ->  payablesService.updateAccountPayable(payablesDtoNegativeAmount.getAccountPayableId(), payablesDtoNegativeAmount));
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when id is invalid")
    void updateAccountPayable_WithInvalidId_ShouldThrowAccountNotFoundException(){
        UUID invalidId = UUID.randomUUID();
        payablesDtoPositiveAmount.setAccountPayableId(invalidId);

        when(payableRepo.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> payablesService.updateAccountPayable(invalidId, payablesDtoPositiveAmount));
    }

    @Test
    @DisplayName("Should return all the accounts successfully")
    void shouldReturnAllTheAccountsSuccessfully(){
        List<AccountPayables> accounts = List.of(
                new AccountPayables(
                        "Description",
                        new BigDecimal("200.00"),
                        LocalDateTime.now(),
                        "Status",
                        "999901",
                        "999902",
                        LocalDateTime.now(),
                        "999903"
                ),
                new AccountPayables(
                        "Description",
                        new BigDecimal("300.00"),
                        LocalDateTime.now(),
                        "Status",
                        "9999012",
                        "9999022",
                        LocalDateTime.now(),
                        "9999032"


                )
        );

        when(payableRepo.findAll()).thenReturn(accounts);
        List<AccountPayablesDto> dto = payablesService.findAll();

        assertEquals(AccountPayablesFactory.toDto(accounts.get(0)).getAccountPayableId(), dto.get(0).getAccountPayableId());
        assertEquals(AccountPayablesFactory.toDto(accounts.get(1)).getAccountPayableId(), dto.get(1).getAccountPayableId());
    }



    @Test
    @DisplayName("Should create account payable successfully when amount is positive")
    void createAccountPayable_WithPositiveAmount_ShouldSucceed() {
        AccountPayables account = AccountPayablesFactory.toAccountPayable(payablesDtoPositiveAmount);
        when(payableRepo.save(account)).thenReturn(account);

        AccountPayablesDto result = payablesService.createAccountPayable(payablesDtoPositiveAmount);

        assertEquals(payablesDtoPositiveAmount, result);
    }

    @Test
    @DisplayName("Should update account payable successfully when ID and amount are valid")
    void updateAccountPayable_WithValidIdAndAmount_ShouldSucceed() {
        UUID id = payablesDtoPositiveAmount.getAccountPayableId();
        AccountPayables existingAccount = new AccountPayables(
                "Old Description",
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                "Old Status",
                "OldSupplier",
                "OldCategory",
                LocalDateTime.now(),
                "OldPaymentMethod"
        );
        existingAccount.setAccountPayableId(id);

        when(payableRepo.findById(id)).thenReturn(Optional.of(existingAccount));
        when(payableRepo.save(any(AccountPayables.class))).thenReturn(existingAccount);

        AccountPayablesDto result = payablesService.updateAccountPayable(id, payablesDtoPositiveAmount);

        assertEquals(payablesDtoPositiveAmount.getDescription(), result.getDescription());
        assertEquals(payablesDtoPositiveAmount.getAmount(), result.getAmount());
    }

    @Test
    @DisplayName("Should delete account payable successfully when ID is valid")
    void deleteAccountPayable_WithValidId_ShouldSucceed() {
        UUID id = UUID.randomUUID();
        AccountPayables existingAccount = new AccountPayables(
                "To be deleted",
                new BigDecimal("100.00"),
                LocalDateTime.now(),
                "Status",
                "Supplier",
                "Category",
                LocalDateTime.now(),
                "PaymentMethod"
        );
        existingAccount.setAccountPayableId(id);

        when(payableRepo.findById(id)).thenReturn(Optional.of(existingAccount));
        doNothing().when(payableRepo).delete(existingAccount);

        assertDoesNotThrow(() -> payablesService.deleteAccountPayable(id));
        verify(payableRepo, times(1)).delete(existingAccount);
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when deleting account with invalid ID")
    void deleteAccountPayable_WithInvalidId_ShouldThrowAccountNotFoundException() {
        UUID invalidId = UUID.randomUUID();
        when(payableRepo.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> payablesService.deleteAccountPayable(invalidId));
    }


}