package com.StraCode.finance.dto;


import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountCategoriesDto implements Serializable {

    private UUID accountCategoriesId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
   private LocalDateTime createdAt;


    public AccountCategoriesDto() {
    }

    public AccountCategoriesDto(UUID accountCategoriesId, String name, String description, LocalDateTime createdAt) {
        this.accountCategoriesId = accountCategoriesId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public UUID getAccountCategoriesId() {
        return accountCategoriesId;
    }

    public void setAccountCategoriesId(UUID accountCategoriesId) {
        this.accountCategoriesId = accountCategoriesId;
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
