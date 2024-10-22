package com.pdqa.enterpriseManagement.service;

import com.pdqa.enterpriseManagement.model.InventoryRecord;
import com.pdqa.enterpriseManagement.model.ProductDetail;
import com.pdqa.enterpriseManagement.model.ProductRecord;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface InventoryService {
    public ResponseEntity<List<ProductRecord>> createProductInfoMapper(List<ProductDetail> listOfProduct,String enterpriseId);
    public ResponseEntity<List<ProductRecord>> storeProduct(String enterpriseId, Map<String, ProductDetail> mappingOfProduct);
    public ResponseEntity<List<ProductRecord>> getAllProductsByEnterpriseId(String enterpriseId);
    public InventoryRecord createInventory(
            String productId,
            Integer costPrice,
            Integer sellingPrice,
            String enterpriseId,
            String storeId,
            String counterId,
            Integer numberOfUnits
    );
    public InventoryRecord getInventory(String productId,String enterpriseId,String storeId);
    public void setInventory(String inventoryId,Integer amount);
}
