package com.pdqa.enterpriseManagement.repo;

import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


@EnableMongoRepositories
public interface EnterpriseRepository extends MongoRepository<EnterpriseRecord, String> {
    @Query("{ 'id': { $eq: ?0 } }")
    public Optional<EnterpriseRecord> findByEnterpriseId(String id);
}
