package com.pdqa.enterpriseManagement.controller;

import com.pdqa.enterpriseManagement.model.InventoryRecord;
import com.pdqa.enterpriseManagement.model.ProductRecord;
import com.pdqa.enterpriseManagement.request.CreateInventoryRequest;
import com.pdqa.enterpriseManagement.request.CreateProductRequestForm;
import com.pdqa.enterpriseManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/gateway/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @PostMapping("/addProduct")
    public ResponseEntity<List<ProductRecord>>createNewProduct(@RequestBody CreateProductRequestForm createProductRequestForm){
        if((!createProductRequestForm.getProductDetails().isEmpty())){
            ResponseEntity<List<ProductRecord>> products = inventoryService.createProductInfoMapper(
                    createProductRequestForm.getProductDetails(),
                    createProductRequestForm.getEnterpriseId()
            );

            return products;
        }
        else{return ResponseEntity.ok(null);}
    }
    @GetMapping("/getProductByEnterprise")
    public ResponseEntity<List<ProductRecord>> getProductRecordByEnterpriseId(@RequestParam("enterpriseId") String enterpriseId){
        return inventoryService.getAllProductsByEnterpriseId(enterpriseId);
    }
    @PostMapping("/createInventoryRecord")
    public ResponseEntity<InventoryRecord> createInventory(@RequestBody CreateInventoryRequest createInventoryRecord){
        String productName= createInventoryRecord.getProductName();
        String enterpriseId = createInventoryRecord.getEnterpriseId();
        Integer costPrice = createInventoryRecord.getCostPrice();
        Integer sellPrice = createInventoryRecord.getSellingPrice();
        Integer quantity = createInventoryRecord.getNumberOfUnits();
        String counterId = createInventoryRecord.getCounterId();
        String storeId = createInventoryRecord.getStoreId();
        return ResponseEntity.ok(inventoryService.createInventory(productName,costPrice,sellPrice,enterpriseId,storeId,counterId,quantity));
    }
    @GetMapping("/getInventory")
    public ResponseEntity<InventoryRecord>getInventory(@RequestParam("productId") String productId,@RequestParam("enterpriseId") String enterpriseId,@RequestParam("storeId")String storeId){
            return ResponseEntity.ok(inventoryService.getInventory(productId,enterpriseId,storeId));
    }
    @GetMapping("/getAllInventory")
    public ResponseEntity<List<InventoryRecord>>getAllInventory(@RequestParam("enterpriseId") String enterpriseId,@RequestParam("storeId")String storeId){
        return ResponseEntity.ok(inventoryService.getAllInventory(enterpriseId,storeId));
    }
    @PutMapping("/updateInventory")
    public ResponseEntity<String>updateInventory(@RequestParam("inventoryId")String inventoryId,@RequestParam("amount")Integer amount){
        inventoryService.setInventory(inventoryId,amount);
        return ResponseEntity.ok("Updated Successfully");
    }
}
