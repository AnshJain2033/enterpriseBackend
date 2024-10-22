package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillingCounterDetail {
    @Id
    String billingCounterId;
    String storeId;
    String entId;
}
