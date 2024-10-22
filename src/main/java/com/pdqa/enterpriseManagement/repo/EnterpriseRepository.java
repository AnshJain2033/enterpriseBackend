package com.pdqa.enterpriseManagement.repo;

import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories
public interface EnterpriseRepository extends MongoRepository<EnterpriseRecord,String> {

}
