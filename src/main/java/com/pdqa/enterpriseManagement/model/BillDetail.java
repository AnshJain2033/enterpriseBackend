package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.List;

@Data
public class BillDetail {
    private List<BilledInventory> listOfProducts;
    private String storeId;
    private String enterpriseId;
}
