package com.StraCode.finance.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "account_categories")
public class AccountCategories {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID accountCategoriesId;

    @Column(length = 45)
    private String name;

    private String description;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime createdAt;

    public AccountCategories(){
    }

    public AccountCategories(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getAccountCategoriesId() {
        return accountCategoriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
