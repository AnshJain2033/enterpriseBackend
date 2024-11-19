package com.pdqa.enterpriseManagement.repo;

import com.pdqa.enterpriseManagement.model.InventoryRecord;
import com.pdqa.enterpriseManagement.model.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryRecord,String>{
    @Query("SELECT ir FROM InventoryRecord ir WHERE ir.enterpriseId = :enterpriseId AND ir.productId = :productId AND ir.storeId = :storeId")
    InventoryRecord findByEnterpriseIdAndStoreIdAndProductId(@Param("enterpriseId")String enterpriseId, @Param("productId")String productId,@Param("storeId")String storeId);
    @Query("SELECT ir FROM InventoryRecord ir WHERE ir.enterpriseId = :enterpriseId AND ir.storeId = :storeId")
    List<InventoryRecord> findByEnterpriseIdAndStoreId(@Param("enterpriseId")String enterpriseId,@Param("storeId")String storeId);
    @Query("UPDATE  ir FROM InventoryRecord SET ir.quantity = :quantity WHERE ir.inventoryId = :inventoryId")
    InventoryRecord findByInventoryIdAndUpdateQuantity(@Param("inventoryId")String inventoryId,@Param("quantity")Integer quantity);
}
