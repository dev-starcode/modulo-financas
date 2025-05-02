package com.StraCode.finance.adapters.controllers;

import com.StraCode.finance.dto.AccountPayablesDto;
import com.StraCode.finance.services.AccountPayablesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/account-payables")
public class AccountPayablesController {

    @Autowired
    private AccountPayablesService payableService;

    @PostMapping
    public ResponseEntity<AccountPayablesDto> create (@Valid @RequestBody AccountPayablesDto payableDto){
            return ResponseEntity.status(HttpStatus.CREATED).body(payableService.createAccountPayable(payableDto));

    }

    @GetMapping
    public ResponseEntity<List<AccountPayablesDto>> findAll (){
        return ResponseEntity.ok(payableService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountPayablesDto> update (@PathVariable UUID id, @Valid @RequestBody AccountPayablesDto payableDto){
        return ResponseEntity.ok().body(payableService.updateAccountPayable(id, payableDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        payableService.deleteAccountPayable(id);
        return ResponseEntity.noContent().build();
    }

}
