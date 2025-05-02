package com.StraCode.finance.adapters.controllers;

import com.StraCode.finance.dto.AccountReceivablesDto;
import com.StraCode.finance.services.AccountReceivablesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account-receivables")
public class AccountReceivablesController {

    @Autowired
    private AccountReceivablesService receivablesService;

    @PostMapping
    public ResponseEntity<AccountReceivablesDto> create(@Valid @RequestBody AccountReceivablesDto receivablesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(receivablesService.createAccountReceivables(receivablesDto));
    }

    @GetMapping
    public ResponseEntity<List<AccountReceivablesDto>> findAll(){
        return  ResponseEntity.ok(receivablesService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountReceivablesDto> update(@PathVariable UUID id, @Valid @RequestBody AccountReceivablesDto receivablesDto){
        return ResponseEntity.ok().body(receivablesService.updateAccountReceivables(id, receivablesDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        receivablesService.deleteAccountReceivable(id);
        return ResponseEntity.noContent().build();
    }
}
