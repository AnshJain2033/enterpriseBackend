package com.pdqa.enterpriseManagement.impl;

import com.pdqa.enterpriseManagement.model.*;
import com.pdqa.enterpriseManagement.repo.BillingRepository;
import com.pdqa.enterpriseManagement.repo.InventoryRepository;
import com.pdqa.enterpriseManagement.repo.ProductRepository;
import com.pdqa.enterpriseManagement.service.BillingService;
import com.pdqa.enterpriseManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    BillingRepository billingRepository;
    public BillingRecord createBill(@RequestBody BillDetail bill){
        List<Pair<ProductKey,Integer>> listOfPurchases =  bill.getListOfProducts();
        String storeId = bill.getStoreId();
        String enterpriseId = bill.getEnterpriseId();
        int billingAmount=0;
        String billingName = bill.getBillingName();
        String billingId = UUID.randomUUID().toString();

        List<Map<ProductKey,Integer>> listOfSellingPriceAndProducts = new ArrayList<>();
        listOfPurchases.forEach((purchase)->{
            InventoryRecord inventoryRecord = inventoryService.getInventory(purchase.getFirst().getProductId(),enterpriseId,storeId);
            if(inventoryRecord.getQuantity()>=purchase.getSecond()){
                Integer currentInventory = inventoryRecord.getQuantity();
                Integer purchasedInventory = purchase.getSecond();
                inventoryService.setInventory(inventoryRecord.getInventoryId(),currentInventory-purchasedInventory);
                Integer sellingPrice = purchase.getSecond()*inventoryRecord.getSellprice();
                Map<ProductKey,Integer> map = new HashMap<>();
                map.put(purchase.getFirst(),sellingPrice);
                listOfSellingPriceAndProducts.add(map);
            }
        });
        BillingRecord billingRecord = new BillingRecord();
        billingRecord.setBillingId(billingId);
        billingRecord.setSellingDate(LocalDate.now());
        billingRecord.setListOfProducts(listOfSellingPriceAndProducts);
//        billingRecord.setPurchaseAmount();
        billingRecord.setBillngName(billingName);
        billingRepository.save(billingRecord);
        return billingRecord;
    }
}
