package com.globalpayments.api.entities;

import java.util.List;

public interface IRecurringEntity {
    Object create() throws Exception;
    Object delete(boolean force) throws Exception;
    Object saveChanges() throws Exception;
}
