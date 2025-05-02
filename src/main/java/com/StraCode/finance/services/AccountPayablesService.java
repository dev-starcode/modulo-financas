package com.StraCode.finance.services;

import com.StraCode.finance.adapters.factories.AccountPayablesFactory;
import com.StraCode.finance.domain.repositories.AccountPayablesRepo;
import com.StraCode.finance.dto.AccountPayablesDto;
import com.StraCode.finance.domain.model.AccountPayables;
import com.StraCode.finance.exceptions.AccountNotFoundException;
import com.StraCode.finance.exceptions.NegativeAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountPayablesService {

    @Autowired
    private AccountPayablesRepo payableRepo;

    public AccountPayablesDto createAccountPayable(AccountPayablesDto payableDto){
        if (payableDto.getAmount().signum() < 0){
            throw new NegativeAmountException("The amount cant be a negative value!");
        }
         payableRepo.save(AccountPayablesFactory.toAccountPayable(payableDto));
         return payableDto;
    }

    public List<AccountPayablesDto> findAll(){
        return payableRepo.findAll()
                .stream()
                .map(AccountPayablesFactory::toDto)
                .collect(Collectors.toList());
    }

    public AccountPayablesDto updateAccountPayable(UUID id, AccountPayablesDto payableDto){
        if (payableDto.getAmount().signum() < 0){
            throw new NegativeAmountException("The amount cant be a negative value!");
        }
        AccountPayables updatedAccount = payableRepo.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account Payables with id: " + id + ", was not found."));
        updatedAccount.setDescription(payableDto.getDescription());
        updatedAccount.setAmount(payableDto.getAmount());
        updatedAccount.setDueDate(payableDto.getDueDate());
        updatedAccount.setPaymentDate(payableDto.getPaymentDate());
        updatedAccount.setStatus(payableDto.getStatus());
        updatedAccount.setSupplierId(payableDto.getSupplierId());
        updatedAccount.setAccountCategoryId(payableDto.getAccountCategoryId());
        updatedAccount.setPaymentMethodId(payableDto.getPaymentMethodId());
        payableRepo.save(updatedAccount);

        return payableDto;

    }

    public void deleteAccountPayable(UUID id){
        AccountPayables accountToBeDeleted = payableRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Payables with id: " + id + ", was not found."));
        payableRepo.delete(accountToBeDeleted);
    }

}
