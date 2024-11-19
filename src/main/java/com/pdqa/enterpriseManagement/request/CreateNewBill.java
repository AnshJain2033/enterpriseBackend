package com.pdqa.enterpriseManagement.request;

import com.pdqa.enterpriseManagement.model.BilledInventory;
import lombok.Data;

import java.util.List;

@Data
public class CreateNewBill {
    private String storeId;
    private String enterpriseId;
    private String counterId;
    private List<BilledInventory> listOfPurchases;
}
