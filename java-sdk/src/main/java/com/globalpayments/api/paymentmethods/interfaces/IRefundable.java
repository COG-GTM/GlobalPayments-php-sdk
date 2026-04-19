package com.globalpayments.api.paymentmethods.interfaces;

import java.math.BigDecimal;

public interface IRefundable {
    Object refund(BigDecimal amount) throws Exception;
}
