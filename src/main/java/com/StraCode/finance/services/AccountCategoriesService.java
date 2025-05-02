package com.StraCode.finance.services;

import com.StraCode.finance.adapters.factories.AccountCategoriesFactory;
import com.StraCode.finance.domain.model.AccountCategories;
import com.StraCode.finance.domain.repositories.AccountCategoriesRepo;
import com.StraCode.finance.dto.AccountCategoriesDto;
import com.StraCode.finance.exceptions.AccountNotFoundException;
import com.StraCode.finance.exceptions.NullDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountCategoriesService {

    @Autowired
    private AccountCategoriesRepo categoriesRepo;


    public AccountCategoriesDto createAccountCategories(AccountCategoriesDto categoriesDto){
        categoriesRepo.save(AccountCategoriesFactory.toAccountCategories(categoriesDto));
        return categoriesDto;
    }

    public List<AccountCategoriesDto> findAll(){
        return categoriesRepo.findAll().stream().map(AccountCategoriesFactory::toDto).collect(Collectors.toList());
    }

    public AccountCategoriesDto updateAccountCategories(UUID id, AccountCategoriesDto categoriesDto){
        AccountCategories updatedAccount = categoriesRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Payables with id: " + id + ", was not found."));
        updatedAccount.setName(categoriesDto.getName());
        updatedAccount.setDescription(categoriesDto.getDescription());
        categoriesRepo.save(updatedAccount);
        return categoriesDto;
    }

    public void deleteAccountCategories(UUID id){
        AccountCategories accountToBeDeleted = categoriesRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Payables with id: " + id + ", was not found."));
        categoriesRepo.delete(accountToBeDeleted);

    }

}
