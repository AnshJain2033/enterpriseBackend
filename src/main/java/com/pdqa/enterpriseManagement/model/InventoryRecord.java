package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "inventory_record")
@Entity
public class InventoryRecord {
        @Id

        @Column(name = "inventoryId")
        private String inventoryId;

        @Column(name = "productId")
        private String productId;
        @Column(name = "enterpriseId")
        private String enterpriseId;

        @Column(name = "storeId")
        private String storeId;

        @Column(name = "counterId")
        private String counterId;

        @Column(name = "quantity")
        private Integer quantity;

        @Column(name = "costprice")
        private long costprice;

        @Column(name = "sellprice")
        private long sellprice;

    }


