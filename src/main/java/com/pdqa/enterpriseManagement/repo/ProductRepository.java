package com.pdqa.enterpriseManagement.repo;


import com.pdqa.enterpriseManagement.model.ProductKey;
import com.pdqa.enterpriseManagement.model.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductRecord, ProductKey>{
    @Query("SELECT pr FROM ProductRecord pr WHERE pr.productKey.enterpriseId = :enterpriseId AND pr.productDetail.productName = :productName")
    ProductRecord findByEnterpriseIdAndProductName(@Param("enterpriseId")String enterpriseId, @Param("productName")String productName);

    @Query("SELECT pr FROM ProductRecord pr WHERE pr.productKey.enterpriseId = :enterpriseId")
    List<ProductRecord> findByEnterpriseId(@Param("enterpriseId")String enterpriseId);

    @Query("SELECT pr FROM ProductRecord pr WHERE pr.productKey.enterpriseId = :enterpriseId AND pr.productKey.productId = :productId")
    ProductRecord findByEnterpriseIdAndProductId(@Param("enterpriseId")String enterpriseId, @Param("productId")String productId);

}
