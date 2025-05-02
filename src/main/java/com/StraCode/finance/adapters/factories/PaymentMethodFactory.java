package com.StraCode.finance.adapters.factories;

import com.StraCode.finance.domain.model.PaymentMethod;
import com.StraCode.finance.dto.PaymentMethodDto;

public  class PaymentMethodFactory {

    public static PaymentMethod toPaymentMethod (PaymentMethodDto paymentMethodDto){
        return new PaymentMethod(paymentMethodDto.getName()
        , paymentMethodDto.getAmount());
    }

    public static PaymentMethodDto toDto (PaymentMethod paymentMethod){
        return new PaymentMethodDto(paymentMethod.getPaymentMethodId()
        , paymentMethod.getName()
        , paymentMethod.getCreatedAt()
        , paymentMethod.getAmount());
    }
}
