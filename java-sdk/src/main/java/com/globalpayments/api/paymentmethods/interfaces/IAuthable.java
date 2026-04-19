package com.globalpayments.api.paymentmethods.interfaces;

import java.math.BigDecimal;

public interface IAuthable {
    Object authorize(BigDecimal amount) throws Exception;
}
