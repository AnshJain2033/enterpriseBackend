package com.pdqa.enterpriseManagement.response;

import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntLoginResponse {
    public EnterpriseRecord enterpriseRecord;
    public String message;
}
