package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InventoryCounterDetail {
    @Id
    String inventoryCounterId;
    String storeId;
    String entId;

}
