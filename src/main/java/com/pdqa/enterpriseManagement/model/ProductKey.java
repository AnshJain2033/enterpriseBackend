package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor
public class ProductKey {
    @Column(name = "enterpriseId")
    public String enterpriseId;
    @Column(name = "productId")
    public String productId;
    // No-argument constructor
    public ProductKey() {}
}
