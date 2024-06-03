package com.usmobileassessment.dailyusageservice.Service;

import com.usmobileassessment.dailyusageservice.Client.CycleClient;
import com.usmobileassessment.dailyusageservice.Dto.CycleDto;
import com.usmobileassessment.dailyusageservice.Dto.DailyUsageResponseDto;
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

    @Autowired
    private CycleClient cycleClient;

    public List<DailyUsage> getDailyUsageByMdn(String mdn) {
        return dailyUsageRepository.findByMdn(mdn);
    }

    public List<DailyUsage> getDailyUsageByUserId(String userId) {
        return dailyUsageRepository.findByUserId(userId);
    }

    public List<DailyUsage> getUsageBetweenDates(Date startDate, Date endDate) {
        return dailyUsageRepository.findByUsageDateBetween(startDate, endDate);
    }

    public List<DailyUsage> getCurrentCycleDailyUsage(String userId, String mdn) {
        CycleDto latestCycle = cycleClient.getLatestCycleHistory(userId, mdn);
        if(latestCycle != null) {
            return dailyUsageRepository.findByUsageDateBetweenAndUserIdAndMdn(latestCycle.getStartDate(),
                    latestCycle.getEndDate(), userId, mdn);
        } else {
            throw new RuntimeException("No Latest Cycle could be found");
        }
    }

    public DailyUsageResponseDto convertToDto(DailyUsage dailyUsage) {
        return DailyUsageResponseDto.builder()
                .date(dailyUsage.getUsageDate())
                .dailyUsageInMb(dailyUsage.getUsedInMb())
                .build();
    }
}
