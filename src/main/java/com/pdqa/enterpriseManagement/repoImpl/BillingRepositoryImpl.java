package com.pdqa.enterpriseManagement.repoImpl;

import com.pdqa.enterpriseManagement.model.BillingRecord;
import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import com.pdqa.enterpriseManagement.repo.BillingRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

public abstract class BillingRepositoryImpl implements BillingRepository {
    @Resource
    @Qualifier("enterpriseMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<BillingRecord> findByBillingId(String enterpriseId) {
        Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("enterpriseId").is(enterpriseId));
        return Optional.ofNullable(mongoTemplate.findOne(query, BillingRecord.class));
    }

}