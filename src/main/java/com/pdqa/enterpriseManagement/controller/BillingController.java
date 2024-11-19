package com.pdqa.enterpriseManagement.controller;

import com.pdqa.enterpriseManagement.model.BillDetail;
import com.pdqa.enterpriseManagement.model.BillingRecord;
import com.pdqa.enterpriseManagement.request.CreateNewBill;
import com.pdqa.enterpriseManagement.request.UpdateExistingBillUsingBillingId;
import com.pdqa.enterpriseManagement.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gateway/billing")
public class BillingController {
    @Autowired
    BillingService billingService;
    @PostMapping("/createBill")
    public ResponseEntity<BillingRecord> createBill(@RequestBody CreateNewBill billDetail){
        return ResponseEntity.ok(billingService.createBill(billDetail));
    }
    @PutMapping("/updateBill")
    public ResponseEntity<BillingRecord> updateBill(@RequestBody UpdateExistingBillUsingBillingId billDetail){
        return ResponseEntity.ok(billingService.updateBill(billDetail));
    }
    @GetMapping("/getBill")
    public ResponseEntity<BillingRecord> getBill(@RequestParam("billingId") String  billingId){
        return ResponseEntity.ok(billingService.getBill(billingId));
    }
}
