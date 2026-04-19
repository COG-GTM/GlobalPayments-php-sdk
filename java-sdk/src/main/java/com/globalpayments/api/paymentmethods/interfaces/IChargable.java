package com.globalpayments.api.paymentmethods.interfaces;

import java.math.BigDecimal;

public interface IChargable {
    Object charge(BigDecimal amount) throws Exception;
}
