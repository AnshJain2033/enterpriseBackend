package com.pdqa.enterpriseManagement.repoImpl;

import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import com.pdqa.enterpriseManagement.repo.EnterpriseRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class EnterpriseRepositoryImpl implements EnterpriseRepository {
    @Resource
    @Qualifier("enterpriseMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<EnterpriseRecord> findByEnterpriseId(String enterpriseId) {
        Query query = new org.springframework.data.mongodb.core.query.Query(Criteria.where("enterpriseId").is(enterpriseId));
        return Optional.ofNullable(mongoTemplate.findOne(query, EnterpriseRecord.class));
    }

}