package com.globalpayments.api.paymentmethods.interfaces;

import java.math.BigDecimal;

public interface IPrePayable {
    Object addValue(BigDecimal amount) throws Exception;
}
