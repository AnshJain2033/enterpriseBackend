package com.pdqa.enterpriseManagement.repo;

import com.pdqa.enterpriseManagement.model.InventoryRecord;
import com.pdqa.enterpriseManagement.model.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryRecord,String>{
    @Query("SELECT ir FROM InventoryRecord ir WHERE ir.enterpriseId = :enterpriseId AND ir.productId = :productId AND ir.storeId = :storeId")
    InventoryRecord findByEnterpriseIdAndStoreIdAndProductId(@Param("enterpriseId")String enterpriseId, @Param("productId")String productId,@Param("storeId")String storeId);

}
