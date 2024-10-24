package com.pdqa.enterpriseManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@Document(collection = "enterpriseRecord")

public class EnterpriseRecord {
    @Id
    String id;

    String enterpriseName;
    List<StoreDetail> listOfStores;
    String password;
}
