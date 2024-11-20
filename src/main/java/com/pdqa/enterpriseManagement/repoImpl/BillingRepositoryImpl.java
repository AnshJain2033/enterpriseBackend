package com.pdqa.enterpriseManagement.repoImpl;

import com.pdqa.enterpriseManagement.model.BilledProducts;
import com.pdqa.enterpriseManagement.model.BillingRecord;
import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import com.pdqa.enterpriseManagement.repo.BillingRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public abstract class BillingRepositoryImpl implements BillingRepository {
    @Resource
    @Qualifier("enterpriseMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<BillingRecord> findByBillingId(String billingId) {
        Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("billingId").is(billingId));
        return Optional.ofNullable(mongoTemplate.findOne(query, BillingRecord.class));
    }
    @Override
    public void updateByBillingId(String id, List<BilledProducts> billedProductsList) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("listOfProducts",billedProductsList);

        mongoTemplate.updateFirst(query, update, BillingRecord.class);
    }

}
