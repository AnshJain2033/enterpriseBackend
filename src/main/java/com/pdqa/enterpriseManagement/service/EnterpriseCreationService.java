package com.pdqa.enterpriseManagement.service;

import com.pdqa.enterpriseManagement.model.StoreDetail;
import com.pdqa.enterpriseManagement.response.EntLoginResponse;
import com.pdqa.enterpriseManagement.response.SignUpPostResponseForm;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public interface EnterpriseCreationService {
    public SignUpPostResponseForm createEnterprise(String enterpriseName, ArrayList<Pair<Integer, Integer>> counterOnStores, String password);
    public List<StoreDetail> createStore(List<Pair<Integer,Integer>>listOfCounters, String entId);
    public ResponseEntity<EntLoginResponse> enterpriseLogin(String enterpriseId, String password);
}
