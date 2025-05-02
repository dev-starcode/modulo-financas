package com.StraCode.finance.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID paymentMethodId;

    @Column(length = 45)
    private String name;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;


    public PaymentMethod(){
    };

    public PaymentMethod(String name, BigDecimal amount) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.amount = amount;
    }

    public UUID getPaymentMethodId() {
        return paymentMethodId;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
