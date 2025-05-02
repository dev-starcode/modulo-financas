package com.StraCode.finance.adapters.controllers;

import com.StraCode.finance.dto.AccountCategoriesDto;
import com.StraCode.finance.services.AccountCategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account-categories")
public class AccountCategoriesController {

    @Autowired
    private AccountCategoriesService categoriesService;


    @PostMapping
    public ResponseEntity<AccountCategoriesDto> create(@Valid @RequestBody AccountCategoriesDto accountCategoriesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriesService.createAccountCategories(accountCategoriesDto));
    }

    @GetMapping
    public ResponseEntity<List<AccountCategoriesDto>> findAll(){
        return ResponseEntity.ok().body(categoriesService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountCategoriesDto> update(@PathVariable UUID id, @Valid @RequestBody AccountCategoriesDto categoriesDto){
        return ResponseEntity.ok().body(categoriesService.updateAccountCategories(id, categoriesDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        categoriesService.deleteAccountCategories(id);
        return ResponseEntity.noContent().build();
    }
}
