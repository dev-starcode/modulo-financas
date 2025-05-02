package com.StraCode.finance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class  Account {

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP", updatable = true, insertable = true)
    private LocalDateTime dueDate;

    @Column(length = 20)
    private String status;

    @Column(length = 36)
    private String accountCategoryId;

    @Column(length = 36)
    private String paymentMethodId;

    public Account(){
    }

    public Account(String description, BigDecimal amount, LocalDateTime dueDate, String status, String accountCategoryId, String paymentMethodId) {
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.accountCategoryId = accountCategoryId;
        this.paymentMethodId = paymentMethodId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountCategoryId() {
        return accountCategoryId;
    }

    public void setAccountCategoryId(String accountCategoryId) {
        this.accountCategoryId = accountCategoryId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
