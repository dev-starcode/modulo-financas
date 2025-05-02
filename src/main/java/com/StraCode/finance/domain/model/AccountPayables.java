package com.StraCode.finance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account_payable")
public class AccountPayables extends Account{
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID accountPayableId;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP", updatable = true, insertable = true)
    private LocalDateTime paymentDate;

    @Column(length = 36)
    private String supplierId;

    public AccountPayables(){
    }

    public AccountPayables(UUID accountPayableId, LocalDateTime paymentDate, String supplierId) {
        this.accountPayableId = accountPayableId;
        this.paymentDate = paymentDate;
        this.supplierId = supplierId;
    }

    public AccountPayables(String description, BigDecimal amount, LocalDateTime dueDate, String status, String accountCategoryId, String paymentMethodId, LocalDateTime paymentDate, String supplierId) {
        super(description, amount, dueDate, status, accountCategoryId, paymentMethodId);
        this.paymentDate = paymentDate;
        this.supplierId = supplierId;
    }

    public UUID getAccountPayableId() {
        return accountPayableId;
    }

    public void setAccountPayableId(UUID accountPayableId) {
        this.accountPayableId = accountPayableId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
