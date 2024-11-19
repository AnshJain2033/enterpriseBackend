package com.pdqa.enterpriseManagement.impl;

import com.pdqa.enterpriseManagement.model.InventoryRecord;
import com.pdqa.enterpriseManagement.model.ProductDetail;
import com.pdqa.enterpriseManagement.model.ProductKey;
import com.pdqa.enterpriseManagement.model.ProductRecord;

import com.pdqa.enterpriseManagement.repo.EnterpriseRepository;
import com.pdqa.enterpriseManagement.repo.InventoryRepository;
import com.pdqa.enterpriseManagement.repo.ProductRepository;
import com.pdqa.enterpriseManagement.request.CreateInventoryRequest;
import com.pdqa.enterpriseManagement.service.InventoryService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Override
    public ResponseEntity<List<ProductRecord>> createProductInfoMapper(List<ProductDetail> listOfProduct, String enterpriseId) {
        Map<String, ProductDetail> mapOfProductToProductId = new HashMap<>();
        listOfProduct.stream().filter(product -> product.getProductName() != null)
                .forEach((productDetail) ->
                        mapOfProductToProductId.put(
                                UUID.randomUUID().toString()
                                , productDetail));


        return storeProduct(enterpriseId, mapOfProductToProductId);
    }

    public ResponseEntity<List<ProductRecord>> storeProduct(String enterpriseId, Map<String, ProductDetail> mappingOfProduct) {

        List<ProductRecord> listOfProducts = new ArrayList<>();
        mappingOfProduct.forEach((productId, productDetail) -> {
            Optional<ProductRecord>existingProduct = Optional.ofNullable(productRepository.findByEnterpriseIdAndProductName(enterpriseId, productDetail.getProductName()));
            if (existingProduct.isEmpty()) {
                ProductKey productKey = new ProductKey(enterpriseId,productId);

                ProductRecord productRecord = new ProductRecord(productKey, productDetail);
                productRepository.save(productRecord);
                listOfProducts.add(productRecord);
            }
        });
        return ResponseEntity.ok(listOfProducts);
    }
    public ResponseEntity<List<ProductRecord>> getAllProductsByEnterpriseId(String enterpriseId){
        if(!enterpriseRepository.findByEnterpriseId(enterpriseId).isEmpty()){
            return ResponseEntity.ok(productRepository.findByEnterpriseId(enterpriseId));
        }
        return null;
    }
    public InventoryRecord createInventory(
            String productName,
    Integer costPrice,
    Integer sellingPrice,
    String enterpriseId,
    String storeId,
    String counterId,
    Integer numberOfUnits
    ) {

        if (!Optional.ofNullable(productRepository.findByEnterpriseIdAndProductName(enterpriseId,productName)).isEmpty()) {
            InventoryRecord inventoryRecord = new InventoryRecord();
            inventoryRecord.setInventoryId(UUID.randomUUID().toString());
            inventoryRecord.setCostprice(costPrice);
            inventoryRecord.setSellprice(sellingPrice);
            inventoryRecord.setQuantity(numberOfUnits);
            inventoryRecord.setEnterpriseId(enterpriseId);
            inventoryRecord.setCounterId(counterId);
            inventoryRecord.setStoreId(storeId);
            inventoryRecord.setProductId(productName);
            inventoryRepository.save(inventoryRecord);
            return inventoryRecord;
        }
        return null;
    }
    public InventoryRecord getInventory(String productId,String enterpriseId,String storeId){
        return inventoryRepository.findByEnterpriseIdAndStoreIdAndProductId(enterpriseId,productId,storeId);
    }
    public void setInventory(String inventoryId,Integer amount){
        inventoryRepository.findById(inventoryId).get().setQuantity(amount);
    }
}
