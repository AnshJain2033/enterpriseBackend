package com.pdqa.enterpriseManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntLoginRequestForm {
    String enterpriseLoginId;
    String password;
}
