package com.StraCode.finance.services;

import com.StraCode.finance.adapters.factories.PaymentMethodFactory;
import com.StraCode.finance.domain.model.PaymentMethod;
import com.StraCode.finance.domain.repositories.PaymentMethodRepo;
import com.StraCode.finance.dto.PaymentMethodDto;
import com.StraCode.finance.exceptions.AccountNotFoundException;
import com.StraCode.finance.exceptions.NegativeAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    @Autowired
    private PaymentMethodRepo paymentMethodRepo;


    public PaymentMethodDto createPaymentMethod(PaymentMethodDto paymentMethodDto){
        if (paymentMethodDto.getAmount().signum() < 0){
            throw new NegativeAmountException("The amount cant be a negative value!");
        }
        paymentMethodRepo.save(PaymentMethodFactory.toPaymentMethod(paymentMethodDto));
        return paymentMethodDto;
    }

    public List<PaymentMethodDto> findAll(){
        return paymentMethodRepo.findAll()
                .stream()
                .map(PaymentMethodFactory::toDto)
                .collect(Collectors.toList());
    }

    public PaymentMethodDto updatePaymentMethod(UUID id, PaymentMethodDto paymentMethodDto){
        if (paymentMethodDto.getAmount().signum() < 0){
            throw new NegativeAmountException("The amount cant be a negative value!");
        }
        PaymentMethod updatedPaymentMethod = paymentMethodRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Payables with id: " + id + ", was not found."));
        updatedPaymentMethod.setName(paymentMethodDto.getName());
        updatedPaymentMethod.setAmount(paymentMethodDto.getAmount());

        paymentMethodRepo.save(updatedPaymentMethod);

        return paymentMethodDto;
    }

    public void deletePaymentMethod(UUID id){
        PaymentMethod toBeDeleted = paymentMethodRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Payables with id: " + id + ", was not found."));
        paymentMethodRepo.delete(toBeDeleted);
    }
}
