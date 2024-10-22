package com.pdqa.enterpriseManagement.request;

import com.pdqa.enterpriseManagement.model.ProductDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateProductRequestForm {
    public List<ProductDetail> productDetails;
    public String enterpriseId;
}
