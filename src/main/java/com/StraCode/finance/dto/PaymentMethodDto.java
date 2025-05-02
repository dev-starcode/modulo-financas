package com.StraCode.finance.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentMethodDto {

    private UUID paymentMethodId;
    @NotBlank
    private String name;
    private LocalDateTime createdAt;
    @NotNull
    private BigDecimal amount;

    public PaymentMethodDto (){

    }

    public PaymentMethodDto(UUID paymentMethodId, String name, LocalDateTime createdAt, BigDecimal amount) {
        this.paymentMethodId = paymentMethodId;
        this.name = name;
        this.createdAt = createdAt;
        this.amount = amount;
    }

    public UUID getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(UUID paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
