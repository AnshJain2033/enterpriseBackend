//package com.pdqa.enterpriseManagement.repoImpl;
//
//import com.pdqa.enterpriseManagement.model.InventoryRecord;
//import com.pdqa.enterpriseManagement.repo.InventoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.transaction.annotation.Transactional;
//
//public class InventoryRepositoryImpl{
//    @Autowired
//    InventoryRepository inventoryRepository;
//    @Transactional
//    public InventoryRecord findByInventoryIdAndUpdateQuantity(String inventoryId, Integer quantity) {
//        InventoryRecord inventoryRecord = inventoryRepository.findById(inventoryId).orElse(null);
//        if (inventoryRecord != null) {
//            inventoryRecord.setQuantity(quantity);
//            return inventoryRepository.save(inventoryRecord);
//        } else {
//            // Handle the case where the inventory record is not found
//            return null; // Or throw an exception
//        }
//    }
//}
