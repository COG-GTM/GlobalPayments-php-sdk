package com.globalpayments.api.paymentmethods.interfaces;

import java.math.BigDecimal;

public interface IReversable {
    Object reverse(BigDecimal amount) throws Exception;
}
