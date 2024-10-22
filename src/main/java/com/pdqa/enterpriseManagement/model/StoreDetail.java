package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StoreDetail {
    @Id
    String storeId;
    List<InventoryCounterDetail>listOfInventoryCounters;
    List<BillingCounterDetail>listOfBillingCounters;


}
