package com.pdqa.enterpriseManagement.impl;

import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import com.pdqa.enterpriseManagement.repo.EnterpriseRepository;
import com.pdqa.enterpriseManagement.model.BillingCounterDetail;
import com.pdqa.enterpriseManagement.model.InventoryCounterDetail;
import com.pdqa.enterpriseManagement.model.StoreDetail;
import com.pdqa.enterpriseManagement.response.EntLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pdqa.enterpriseManagement.response.SignUpPostResponseForm;
import com.pdqa.enterpriseManagement.service.EnterpriseCreationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class EnterpriseCreationServiceImpl implements EnterpriseCreationService {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Override
    public SignUpPostResponseForm createEnterprise(String enterpriseName,ArrayList<ArrayList<Integer>> counterOnStores,String password){
        String entId=UUID.randomUUID().toString();
        List<StoreDetail>listOfStores = createStore(counterOnStores,entId);
        enterpriseRepository.insert(new EnterpriseRecord(entId,enterpriseName,listOfStores,password));
        return new SignUpPostResponseForm(entId,listOfStores);
    }
    @Override
    public List<StoreDetail> createStore(List<ArrayList<Integer>> listOfCounterRequired,String entId){
        List<StoreDetail> listOfStores= new ArrayList<>();
        for(int i=0;i<listOfCounterRequired.size();i++){
            String storeId= UUID.randomUUID().toString();
            List<BillingCounterDetail> billingCounterDetails = new ArrayList<>();
            List<InventoryCounterDetail> inventoryCounterDetails = new ArrayList<>();
            for(int j=0;j<listOfCounterRequired.get(i).get(0);j++){
                inventoryCounterDetails.add(new InventoryCounterDetail(UUID.randomUUID().toString(),storeId,entId));
            }
            for(int j=0;j<listOfCounterRequired.get(i).get(1);j++){
                billingCounterDetails.add(new BillingCounterDetail(UUID.randomUUID().toString(),storeId,entId));
            }
            listOfStores.add(new StoreDetail(storeId,inventoryCounterDetails,billingCounterDetails));
        }
        return listOfStores;
    }
    @Override
    public ResponseEntity<EntLoginResponse> enterpriseLogin(String enterpriseId, String password){
        Optional<EnterpriseRecord> enterpriseRecord = enterpriseRepository.findByEnterpriseId(enterpriseId);
        if(!enterpriseRecord.isEmpty()){
            if(enterpriseRecord.get().getPassword().equals(password)){
                return ResponseEntity.ok(new EntLoginResponse(enterpriseRecord.get(),"Login Successful"));
            }
            else{return ResponseEntity.ok(new EntLoginResponse(null,"Wrong Password"));}
        }else{return ResponseEntity.ok(new EntLoginResponse(null,"Invalid Enterprise ID"));}
    }
}
