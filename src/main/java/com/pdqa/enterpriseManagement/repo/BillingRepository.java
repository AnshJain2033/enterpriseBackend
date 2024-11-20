package com.pdqa.enterpriseManagement.repo;

import com.pdqa.enterpriseManagement.model.BilledProducts;
import com.pdqa.enterpriseManagement.model.BillingRecord;
import com.pdqa.enterpriseManagement.model.EnterpriseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface BillingRepository extends MongoRepository<BillingRecord,String> {
    @Query("{ 'id': { $eq: ?0 } }")
    public Optional<BillingRecord> findByBillingId(String id);
    BilledProducts save(BilledProducts billedProducts);
    public void updateByBillingId(String id, List<BilledProducts> billedProductsList);
}
