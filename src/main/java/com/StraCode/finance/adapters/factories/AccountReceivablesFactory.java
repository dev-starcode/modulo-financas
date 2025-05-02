package com.StraCode.finance.adapters.factories;

import com.StraCode.finance.dto.AccountReceivablesDto;
import com.StraCode.finance.domain.model.AccountReceivables;

public class AccountReceivablesFactory {

    public static AccountReceivables toAccountReceivables(AccountReceivablesDto receivablesDto){
        return new AccountReceivables(receivablesDto.getDescription(),
                receivablesDto.getAmount(),
                receivablesDto.getDueDate(),
                receivablesDto.getStatus(),
                receivablesDto.getAccountCategoryId(),
                receivablesDto.getPaymentMethodId(),
                receivablesDto.getReceivedDate(),
                receivablesDto.getClientId());
    }

    public static AccountReceivablesDto toDto(AccountReceivables receivables){
        return new AccountReceivablesDto(receivables.getAccountReceivablesId(),
                receivables.getDescription(),
                receivables.getAmount(),
                receivables.getDueDate(),
                receivables.getStatus(),
                receivables.getReceivedDate(),
                receivables.getClientId(),
                receivables.getAccountCategoryId(),
                receivables.getPaymentMethodId());
    }
}
