package com.pdqa.enterpriseManagement.impl;

import com.pdqa.enterpriseManagement.model.*;
import com.pdqa.enterpriseManagement.repo.BillingRepository;
import com.pdqa.enterpriseManagement.repo.InventoryRepository;
import com.pdqa.enterpriseManagement.repo.ProductRepository;
import com.pdqa.enterpriseManagement.request.CreateNewBill;
import com.pdqa.enterpriseManagement.request.UpdateExistingBillUsingBillingId;
import com.pdqa.enterpriseManagement.service.BillingService;
import com.pdqa.enterpriseManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    BillingRepository billingRepository;
    @Autowired
    ProductRepository productRepository;

    public BillingRecord createBill(@RequestBody CreateNewBill bill){
        List<BilledInventory> listOfPurchases =  bill.getListOfPurchases();
        String storeId = bill.getStoreId();
        String enterpriseId = bill.getEnterpriseId();
        String counterId = bill.getCounterId();


        int billingAmount=0;
        String billingId = UUID.randomUUID().toString();
        List<BilledProducts> billedProductsList = new ArrayList<>();

        listOfPurchases.forEach((purchase)->{
            ProductRecord product = productRepository.findByEnterpriseIdAndProductName(enterpriseId,purchase.getProductName());
            InventoryRecord inventoryRecord = inventoryService.getInventory(product.getProductKey().getProductId(),enterpriseId,storeId);
            if(inventoryRecord.getQuantity()>=purchase.getBilledUnits()){
                Integer currentInventory = inventoryRecord.getQuantity();
                Integer purchasedInventory = purchase.getBilledUnits();
                inventoryService.setInventory(inventoryRecord.getInventoryId(),currentInventory-purchasedInventory);
                Integer sellingPrice = purchase.getBilledUnits()*inventoryRecord.getSellprice();
                BilledProducts billedProducts = new BilledProducts(product.getProductKey().getProductId(),sellingPrice,purchase.getBilledUnits(),counterId,enterpriseId,storeId);
                billedProductsList.add(billedProducts);
            }
        });
        BillingRecord billingRecord = new BillingRecord();
        billingRecord.setBillingId(billingId);
        billingRecord.setSellingDate(LocalDate.now());
        billingRecord.setListOfProducts(billedProductsList);
        billingRepository.save(billingRecord);
        return billingRecord;
    }
    public BillingRecord updateBill(UpdateExistingBillUsingBillingId bill){
        String billingId = bill.getBillingId();
        Optional<BillingRecord> billingRecord = billingRepository.findById(billingId);
        if(!billingRecord.isEmpty()){
            List<BilledProducts> existingListOfBilledProducts = new ArrayList<>(billingRecord.get().getListOfProducts());
            List<BilledProducts>billedProductsList = new ArrayList<>(helperFunction(bill.getBill()));
            existingListOfBilledProducts.addAll(billedProductsList);
            billingRecord.get().setListOfProducts(existingListOfBilledProducts);
            billingRepository.insert(billingRecord.get());
        }
        return billingRecord.get();
    }
    List<BilledProducts> helperFunction(CreateNewBill bill){
        List<BilledInventory> listOfPurchases =  bill.getListOfPurchases();
        String storeId = bill.getStoreId();
        String enterpriseId = bill.getEnterpriseId();
        String counterId = bill.getCounterId();

        List<BilledProducts> billedProductsList = new ArrayList<>();

        listOfPurchases.forEach((purchase)->{
            ProductRecord product = productRepository.findByEnterpriseIdAndProductName(enterpriseId,purchase.getProductName());
            InventoryRecord inventoryRecord = inventoryService.getInventory(product.getProductKey().getProductId(),enterpriseId,storeId);
            if(inventoryRecord.getQuantity()>=purchase.getBilledUnits()){
                Integer currentInventory = inventoryRecord.getQuantity();
                Integer purchasedInventory = purchase.getBilledUnits();
                inventoryService.setInventory(inventoryRecord.getInventoryId(),currentInventory-purchasedInventory);
                Integer sellingPrice = purchase.getBilledUnits()*inventoryRecord.getSellprice();
                BilledProducts billedProducts = new BilledProducts(product.getProductKey().getProductId(),sellingPrice,purchase.getBilledUnits(),counterId,enterpriseId,storeId);
                billedProductsList.add(billedProducts);
            }
        });
        return billedProductsList;
    }

}
