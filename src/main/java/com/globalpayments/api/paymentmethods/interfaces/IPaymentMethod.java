package com.globalpayments.api.paymentmethods.interfaces;

import com.globalpayments.api.entities.enums.PaymentMethodType;

public interface IPaymentMethod {
    PaymentMethodType getPaymentMethodType();
}
