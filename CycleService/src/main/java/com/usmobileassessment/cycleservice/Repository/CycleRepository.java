package com.usmobileassessment.cycleservice.Repository;

import com.usmobileassessment.cycleservice.Model.Cycle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CycleRepository extends MongoRepository<Cycle, String> {
    List<Cycle> findByUserId(String userId);
    List<Cycle> findByMdn(String mdn);
    List<Cycle> findByUserIdAndMdn(String userId, String mdn);

}
