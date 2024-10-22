package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
public class ProductDetail {
    @Column(name = "productName")
    public String productName;
    @Column(name = "productCategory")
    public String productCategory;
    @Column(name = "description")
    public String description;
}
