package com.pdqa.enterpriseManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data

public class BilledProducts {
    private String productId;
    private Integer billingPrice;
    private Integer billedQuantity;
    private String counterId;
    private String enterpriseId;
    private String storeId;
}
