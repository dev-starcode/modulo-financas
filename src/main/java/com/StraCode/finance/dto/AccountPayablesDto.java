package com.StraCode.finance.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountPayablesDto implements Serializable {

    private UUID accountPayableId;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDateTime dueDate;
    @NotNull
    private LocalDateTime paymentDate;
    @NotBlank
    private String status;
    @NotBlank
    private String supplierId;
    @NotBlank
    private String accountCategoryId;
    @NotBlank
    private String paymentMethodId;


    public AccountPayablesDto(){
    }

    public AccountPayablesDto(UUID accountPayableId, String description, BigDecimal amount, LocalDateTime dueDate, LocalDateTime paymentDate, String status, String supplierId, String accountCategoryId, String paymentMethodId) {
        this.accountPayableId = accountPayableId;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
        this.status = status;
        this.supplierId = supplierId;
        this.accountCategoryId = accountCategoryId;
        this.paymentMethodId = paymentMethodId;
    }

    public UUID getAccountPayableId() {
        return accountPayableId;
    }

    public void setAccountPayableId(UUID accountPayableId) {
        this.accountPayableId = accountPayableId;
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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
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
