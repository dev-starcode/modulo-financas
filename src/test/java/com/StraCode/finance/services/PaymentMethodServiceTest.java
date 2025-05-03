package com.StraCode.finance.services;

import com.StraCode.finance.domain.model.PaymentMethod;
import com.StraCode.finance.domain.repositories.PaymentMethodRepo;
import com.StraCode.finance.dto.PaymentMethodDto;
import com.StraCode.finance.exceptions.AccountNotFoundException;
import com.StraCode.finance.exceptions.NegativeAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentMethodServiceTest {

    @Mock
    private PaymentMethodRepo paymentMethodRepo;

    @InjectMocks
    private PaymentMethodService paymentMethodService;

    private PaymentMethodDto dtoNegative;
    private PaymentMethodDto dtoPositive;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dtoNegative = new PaymentMethodDto(UUID.randomUUID(), "Credit", LocalDateTime.now(), new BigDecimal("-20.00"));
        dtoPositive = new PaymentMethodDto(UUID.randomUUID(), "Debit", LocalDateTime.now(), new BigDecimal("20.00"));
    }

    @Test
    @DisplayName("Should throw NegativeAmountException when amount is negative")
    void createPaymentMethod_WithNegativeAmount_ShouldThrowNegativeAmountException() {
        assertThrows(NegativeAmountException.class, () ->
                paymentMethodService.createPaymentMethod(dtoNegative));
    }

    @Test
    @DisplayName("Should create payment method when amount is valid")
    void createPaymentMethod_WithValidAmount_ShouldSucceed() {
        PaymentMethod paymentMethod = new PaymentMethod(dtoPositive.getName(), dtoPositive.getAmount());
        when(paymentMethodRepo.save(any(PaymentMethod.class))).thenReturn(paymentMethod);

        PaymentMethodDto result = paymentMethodService.createPaymentMethod(dtoPositive);

        assertEquals(dtoPositive, result);
    }

    @Test
    @DisplayName("Should return all payment methods successfully")
    void findAll_ShouldReturnAllPaymentMethods() {
        List<PaymentMethod> list = List.of(
                new PaymentMethod("Credit", new BigDecimal("10.00")),
                new PaymentMethod("Debit", new BigDecimal("20.00"))
        );

        when(paymentMethodRepo.findAll()).thenReturn(list);

        List<PaymentMethodDto> result = paymentMethodService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Should update payment method successfully when ID and amount are valid")
    void updatePaymentMethod_WithValidIdAndAmount_ShouldSucceed() {
        UUID id = dtoPositive.getPaymentMethodId();
        PaymentMethod existing = new PaymentMethod("OldName", new BigDecimal("10.00"));
        ReflectionTestUtils.setField(existing, "paymentMethodId", id);

        when(paymentMethodRepo.findById(id)).thenReturn(Optional.of(existing));
        when(paymentMethodRepo.save(any(PaymentMethod.class))).thenReturn(existing);

        PaymentMethodDto result = paymentMethodService.updatePaymentMethod(id, dtoPositive);

        assertEquals(dtoPositive.getName(), result.getName());
        assertEquals(dtoPositive.getAmount(), result.getAmount());
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when updating with invalid ID")
    void updatePaymentMethod_WithInvalidId_ShouldThrowException() {
        UUID id = UUID.randomUUID();

        when(paymentMethodRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () ->
                paymentMethodService.updatePaymentMethod(id, dtoPositive));
    }

    @Test
    @DisplayName("Should delete payment method when ID is valid")
    void deletePaymentMethod_WithValidId_ShouldSucceed() {
        UUID id = UUID.randomUUID();
        PaymentMethod existing = new PaymentMethod("ToDelete", new BigDecimal("30.00"));
        ReflectionTestUtils.setField(existing, "paymentMethodId", id);

        when(paymentMethodRepo.findById(id)).thenReturn(Optional.of(existing));
        doNothing().when(paymentMethodRepo).delete(existing);

        assertDoesNotThrow(() -> paymentMethodService.deletePaymentMethod(id));
        verify(paymentMethodRepo, times(1)).delete(existing);
    }

    @Test
    @DisplayName("Should throw AccountNotFoundException when deleting with invalid ID")
    void deletePaymentMethod_WithInvalidId_ShouldThrowException() {
        UUID id = UUID.randomUUID();
        when(paymentMethodRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () ->
                paymentMethodService.deletePaymentMethod(id));
    }
}
