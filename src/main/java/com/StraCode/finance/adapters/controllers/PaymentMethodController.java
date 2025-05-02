package com.StraCode.finance.adapters.controllers;

import com.StraCode.finance.dto.PaymentMethodDto;
import com.StraCode.finance.services.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<PaymentMethodDto> create (@Valid @RequestBody PaymentMethodDto paymentMethodDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodService.createPaymentMethod(paymentMethodDto));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> findAll (){
        return ResponseEntity.ok(paymentMethodService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDto> update (@PathVariable UUID id, @Valid @RequestBody PaymentMethodDto paymentMethodDto){
        return ResponseEntity.ok(paymentMethodService.updatePaymentMethod(id, paymentMethodDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable UUID id){
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}
