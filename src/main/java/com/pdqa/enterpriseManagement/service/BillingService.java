package com.pdqa.enterpriseManagement.service;

import com.pdqa.enterpriseManagement.model.BillDetail;
import com.pdqa.enterpriseManagement.model.BillingRecord;
import com.pdqa.enterpriseManagement.request.CreateNewBill;
import com.pdqa.enterpriseManagement.request.UpdateExistingBillUsingBillingId;

public interface BillingService {
    public BillingRecord createBill(CreateNewBill bill);
    public BillingRecord updateBill(UpdateExistingBillUsingBillingId bill);
}
