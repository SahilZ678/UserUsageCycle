package com.usmobileassessment.dailyusageservice.Service;

import com.usmobileassessment.dailyusageservice.Model.DailyUsage;
import com.usmobileassessment.dailyusageservice.Repository.DailyUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyUsageService {
    @Autowired
    private DailyUsageRepository dailyUsageRepository;

    public List<DailyUsage> getDailyUsageByMdn(String mdn) {
        return dailyUsageRepository.findByMdn(mdn);
    }

    public List<DailyUsage> getDailyUsageByUserId(String userId) {
        return dailyUsageRepository.findByUserId(userId);
    }

    public List<DailyUsage> getUsageBetweenDates(Date startDate, Date endDate) {
        return dailyUsageRepository.findByUsageDateBetween(startDate, endDate);
    }
}
