package com.pdqa.enterpriseManagement.request;

import lombok.Data;

@Data
public class CreateInventoryRequest {
    String productName;
    Integer costPrice;
    Integer sellingPrice;
    String enterpriseId;
    String storeId;
    String counterId;
    Integer numberOfUnits;
}
