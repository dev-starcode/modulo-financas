package com.StraCode.finance.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountReceivablesDto implements Serializable {

    private UUID accountReceivablesID;
    @NotBlank
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDateTime dueDate;
    @NotBlank
    private String status;
    private LocalDateTime receivedDate;
    @NotBlank
    private String clientId;
    @NotBlank
    private String accountCategoryId;
    @NotBlank
    private String paymentMethodId;

    public AccountReceivablesDto(){

    }

    public AccountReceivablesDto(UUID accountReceivablesID, String description, BigDecimal amount, LocalDateTime dueDate, String status, LocalDateTime receivedDate, String clientId, String accountCategoryId, String paymentMethodId) {
        this.accountReceivablesID = accountReceivablesID;
        this.description = description;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.receivedDate = receivedDate;
        this.clientId = clientId;
        this.accountCategoryId = accountCategoryId;
        this.paymentMethodId = paymentMethodId;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getAccountReceivablesID() {
        return accountReceivablesID;
    }

    public void setAccountReceivablesID(UUID accountReceivablesID) {
        this.accountReceivablesID = accountReceivablesID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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