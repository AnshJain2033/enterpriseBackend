package com.pdqa.enterpriseManagement.service;

import com.pdqa.enterpriseManagement.model.BillDetail;
import com.pdqa.enterpriseManagement.model.BillingRecord;

public interface BillingService {
    public BillingRecord createBill(BillDetail bill);
}
