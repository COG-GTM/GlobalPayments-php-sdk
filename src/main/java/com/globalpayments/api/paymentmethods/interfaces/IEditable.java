package com.globalpayments.api.paymentmethods.interfaces;

import java.math.BigDecimal;

public interface IEditable {
    Object edit(BigDecimal amount) throws Exception;
}
