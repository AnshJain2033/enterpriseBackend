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
            Optional<ProductRecord> product = Optional.ofNullable(productRepository.findByEnterpriseIdAndProductName(enterpriseId,purchase.getProductName()));
            if(!product.isEmpty()) {
                Optional<InventoryRecord> inventoryRecord = Optional.ofNullable(inventoryService.getInventory(product.get().getProductKey().getProductId(), enterpriseId, storeId));
                if ((!inventoryRecord.isEmpty())&&inventoryRecord.get().getQuantity() >= purchase.getBilledUnits() ) {
                    Integer currentInventory = inventoryRecord.get().getQuantity();
                    Integer purchasedInventory = purchase.getBilledUnits();
                    inventoryService.setInventory(inventoryRecord.get().getInventoryId(), currentInventory - purchasedInventory);
                    Integer sellingPrice = purchase.getBilledUnits() * inventoryRecord.get().getSellprice();
                    BilledProducts billedProducts = new BilledProducts(product.get().getProductKey().getProductId(), sellingPrice, purchase.getBilledUnits(), counterId, enterpriseId, storeId);
                    billedProductsList.add(billedProducts);
                }
            }
        });
        if(!billedProductsList.isEmpty()) {
            BillingRecord billingRecord = new BillingRecord();
            billingRecord.setBillingId(billingId);
            billingRecord.setSellingDate(LocalDate.now());
            billingRecord.setListOfProducts(billedProductsList);
            billingRepository.save(billingRecord);
            return billingRecord;
        }
        return null;
    }
    public BillingRecord updateBill(UpdateExistingBillUsingBillingId bill){
        String billingId = bill.getBillingId();
        Optional<BillingRecord> billingRecord = billingRepository.findByBillingId(billingId);
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
            Optional<ProductRecord> product = Optional.ofNullable(productRepository.findByEnterpriseIdAndProductName(enterpriseId,purchase.getProductName()));
            if(!product.isEmpty()) {
                Optional<InventoryRecord> inventoryRecord = Optional.ofNullable(inventoryService.getInventory(product.get().getProductKey().getProductId(), enterpriseId, storeId));
                if ((!inventoryRecord.isEmpty())&&inventoryRecord.get().getQuantity() >= purchase.getBilledUnits() ) {
                    Integer currentInventory = inventoryRecord.get().getQuantity();
                    Integer purchasedInventory = purchase.getBilledUnits();
                    inventoryService.setInventory(inventoryRecord.get().getInventoryId(), currentInventory - purchasedInventory);
                    Integer sellingPrice = purchase.getBilledUnits() * inventoryRecord.get().getSellprice();
                    BilledProducts billedProducts = new BilledProducts(product.get().getProductKey().getProductId(), sellingPrice, purchase.getBilledUnits(), counterId, enterpriseId, storeId);
                    billedProductsList.add(billedProducts);
                }
            }
        });
        return billedProductsList;
    }
    public BillingRecord getBill(String billingId){
        Optional<BillingRecord> billingRecord = billingRepository.findByBillingId(billingId);
        if(!billingRecord.isEmpty()){
            return billingRecord.get();
        }
        return null;
    }
}
