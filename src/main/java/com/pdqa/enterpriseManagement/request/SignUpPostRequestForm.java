package com.pdqa.enterpriseManagement.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class SignUpPostRequestForm {
    String enterpriseName;
    ArrayList<ArrayList<Integer>> listOfStoreDetails;
    String password;
}
