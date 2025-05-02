package com.StraCode.finance.adapters.factories;

import com.StraCode.finance.dto.AccountPayablesDto;
import com.StraCode.finance.domain.model.AccountPayables;

public class AccountPayablesFactory {

    public static AccountPayables toAccountPayable(AccountPayablesDto payableDto){
        return new AccountPayables(payableDto.getDescription(),
                payableDto.getAmount(),
                payableDto.getDueDate(),
                payableDto.getStatus(),
                payableDto.getAccountCategoryId(),
                payableDto.getPaymentMethodId(),
                payableDto.getPaymentDate(),
                payableDto.getSupplierId());
    }

    public static AccountPayablesDto toDto(AccountPayables payable){
        return new AccountPayablesDto(payable.getAccountPayableId(),
                payable.getDescription(),
                payable.getAmount(),
                payable.getDueDate(),
                payable.getPaymentDate(),
                payable.getStatus(),
                payable.getSupplierId(),
                payable.getAccountCategoryId(),
                payable.getPaymentMethodId());
    }



}
