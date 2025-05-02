package com.StraCode.finance.adapters.factories;

import com.StraCode.finance.domain.model.AccountCategories;
import com.StraCode.finance.dto.AccountCategoriesDto;

public class AccountCategoriesFactory {

    public static AccountCategories toAccountCategories(AccountCategoriesDto categoriesDto){
        return new AccountCategories(categoriesDto.getName()
        ,categoriesDto.getDescription());
    }

    public static AccountCategoriesDto toDto(AccountCategories accountCategories){
        return new AccountCategoriesDto(accountCategories.getAccountCategoriesId(), accountCategories.getName(), accountCategories.getDescription(), accountCategories.getCreatedAt());
    }
}
