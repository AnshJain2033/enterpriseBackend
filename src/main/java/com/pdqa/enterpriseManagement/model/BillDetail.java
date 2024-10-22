package com.pdqa.enterpriseManagement.model;

import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.List;

@Data
public class BillDetail {
    private List<Pair<ProductKey,Integer>> listOfProducts;
    private String billingName;
    private String storeId;
    private String enterpriseId;
}
