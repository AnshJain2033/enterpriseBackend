package com.pdqa.enterpriseManagement.response;

import com.pdqa.enterpriseManagement.model.StoreDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SignUpPostResponseForm {
    String entId;
    List<StoreDetail> listOfStoreDetails;
}
