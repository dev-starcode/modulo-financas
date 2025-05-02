package com.StraCode.finance.services;

import com.StraCode.finance.adapters.factories.AccountReceivablesFactory;
import com.StraCode.finance.domain.repositories.AccountReceivablesRepo;
import com.StraCode.finance.dto.AccountReceivablesDto;
import com.StraCode.finance.domain.model.AccountReceivables;
import com.StraCode.finance.exceptions.AccountNotFoundException;
import com.StraCode.finance.exceptions.NegativeAmountException;
import com.StraCode.finance.exceptions.NullDtoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountReceivablesService {

    @Autowired
    public AccountReceivablesRepo receivablesRepo;

    public AccountReceivablesDto createAccountReceivables(AccountReceivablesDto receivablesDto){
        if (receivablesDto.getAmount().signum() < 0){
            throw new NegativeAmountException("The amount cant be a negative value!");
        }
        receivablesRepo.save(AccountReceivablesFactory.toAccountReceivables(receivablesDto));
        return receivablesDto;
    }

    public List<AccountReceivablesDto> findAll(){
        return receivablesRepo.findAll().stream().map(AccountReceivablesFactory::toDto).collect(Collectors.toList());
    }

    public AccountReceivablesDto updateAccountReceivables(UUID id, AccountReceivablesDto  receivablesDto){
        if (receivablesDto.getAmount().signum() < 0){
            throw new NegativeAmountException("The amount cant be a negative value!");
        }
        AccountReceivables updatedAccount = receivablesRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Receivables with id: " + id + ", was not found."));

        updatedAccount.setDescription(receivablesDto.getDescription());
        updatedAccount.setAmount(receivablesDto.getAmount());
        updatedAccount.setDueDate(receivablesDto.getDueDate());
        updatedAccount.setStatus(receivablesDto.getStatus());
        updatedAccount.setAccountCategoryId(receivablesDto.getAccountCategoryId());
        updatedAccount.setPaymentMethodId(receivablesDto.getPaymentMethodId());
        updatedAccount.setReceivedDate(receivablesDto.getReceivedDate());
        updatedAccount.setClientId(receivablesDto.getClientId());

        receivablesRepo.save(updatedAccount);
        return receivablesDto;
    }

    public void deleteAccountReceivable(UUID id){
        AccountReceivables accountToBeDeleted = receivablesRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account Receivables with id: " + id + ", was not found."));
        receivablesRepo.delete(accountToBeDeleted);
    }
}
