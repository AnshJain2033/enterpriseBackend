package com.pdqa.enterpriseManagement.request;

import com.pdqa.enterpriseManagement.model.ProductKey;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.List;

@Data
public class UpdateExistingBillUsingBillingId {
    private String billingId;
    private CreateNewBill bill;
}
