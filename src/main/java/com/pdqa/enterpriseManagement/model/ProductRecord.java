package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@AllArgsConstructor
@Table(name = "product_record")
public class ProductRecord {

        @EmbeddedId
        @Column(name = "productKey")
        private ProductKey productKey;

        @Embedded
        @Column(name = "productDetail")
        private ProductDetail productDetail;
}
