package com.usmobileassessment.dailyusageservice.Repository;

import com.usmobileassessment.dailyusageservice.Model.DailyUsage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyUsageRepository extends MongoRepository<DailyUsage, String> {
    List<DailyUsage> findByMdn(String mdn);
    List<DailyUsage> findByUserId(String userId);
    List<DailyUsage> findByUserIdAndMdn(String userId, String mdn);
    List<DailyUsage> findByUsageDateBetween(Date startDate, Date endDate);
    List<DailyUsage> findByUsageDateBetweenAndUserIdAndMdn(Date startDate, Date endDate, String userId, String mdn);

}
