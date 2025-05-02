package com.StraCode.finance.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account_receivables")
public class AccountReceivables extends Account{

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID accountReceivablesId;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP", updatable = true, insertable = true)
    private LocalDateTime receivedDate;

    @Column(length = 36)
    private String clientId;

    public AccountReceivables(){
    }

    public AccountReceivables(UUID accountReceivablesId, LocalDateTime receivedDate, String clientId) {
        this.accountReceivablesId = accountReceivablesId;
        this.receivedDate = receivedDate;
        this.clientId = clientId;
    }

    public AccountReceivables(String description, BigDecimal amount, LocalDateTime dueDate, String status, String accountCategoryId, String paymentMethodId, LocalDateTime receivedDate, String clientId) {
        super(description, amount, dueDate, status, accountCategoryId, paymentMethodId);
        this.receivedDate = receivedDate;
        this.clientId = clientId;
    }

    public UUID getAccountReceivablesId() {
        return accountReceivablesId;
    }

    public void setAccountReceivablesId(UUID accountReceivablesId) {
        this.accountReceivablesId = accountReceivablesId;
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
}
