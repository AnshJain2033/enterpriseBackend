package com.pdqa.enterpriseManagement.controller;

import com.pdqa.enterpriseManagement.request.EntLoginRequestForm;
import com.pdqa.enterpriseManagement.response.EntLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pdqa.enterpriseManagement.request.SignUpPostRequestForm;
import com.pdqa.enterpriseManagement.response.SignUpPostResponseForm;
import com.pdqa.enterpriseManagement.service.EnterpriseCreationService;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/gateway")
public class EnterpriseController {
    @Autowired
    EnterpriseCreationService enterpriseCreationService;
    @GetMapping("/hello")
    public String getHelloController(){
        return "hello";
    }
    @PostMapping(value = "/signup")
    public ResponseEntity<SignUpPostResponseForm> signUpPostController(@RequestBody SignUpPostRequestForm signUpPostRequestForm){
        String enterpriseName=signUpPostRequestForm.getEnterpriseName();
        ArrayList<Pair<Integer, Integer>> counterOnStores = signUpPostRequestForm.getListOfStoreDetails();
        String password =signUpPostRequestForm.getPassword();
        SignUpPostResponseForm signUpPostResponseForm = enterpriseCreationService.createEnterprise(enterpriseName,counterOnStores,password);
        return new ResponseEntity<SignUpPostResponseForm>(signUpPostResponseForm,HttpStatus.CREATED);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<EntLoginResponse> loginToEnterpriseController(@RequestBody EntLoginRequestForm enterpriseLogin){
        return enterpriseCreationService.enterpriseLogin(
                enterpriseLogin.getEnterpriseLoginId(),
                enterpriseLogin.getPassword()
        );
    }
}
