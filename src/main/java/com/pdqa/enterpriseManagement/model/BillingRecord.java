package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity

public class BillingRecord {
    @Id
    @Column(name = "billingId")
    private String billingId;

    @Column(name = "productId")
    private String productId;

    @Column(name = "sellingPrice")
    private Integer sellingprice;

    @Column(name = "date")
    private Date sellingDate;

}
