package com.usmobileassessment.dailyusageservice.Service;

import com.usmobileassessment.dailyusageservice.Client.CycleClient;
import com.usmobileassessment.dailyusageservice.Dto.CycleDto;
import com.usmobileassessment.dailyusageservice.Model.DailyUsage;
import com.usmobileassessment.dailyusageservice.Repository.DailyUsageRepository;
import com.usmobileassessment.dailyusageservice.Service.DailyUsageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DailyUsageServiceTest {
    @Mock
    private DailyUsageRepository dailyUsageRepository;

    @Mock
    private CycleClient cycleClient;

    @InjectMocks
    private DailyUsageService dailyUsageService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCurrentCycleDailyUsage() {
        String userId = "user123";
        String mdn = "1234567890";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 86400000L);

        CycleDto cycleResponseDto = new CycleDto();
        cycleResponseDto.setStartDate(startDate);
        cycleResponseDto.setEndDate(endDate);

        DailyUsage usage1 = new DailyUsage();
        usage1.setUsageDate(startDate);
        usage1.setUserId(userId);
        usage1.setMdn(mdn);
        usage1.setUsedInMb(100.00);

        DailyUsage usage2 = new DailyUsage();
        usage2.setUsageDate(endDate);
        usage2.setUserId(userId);
        usage2.setMdn(mdn);
        usage2.setUsedInMb(200.00);

        List<DailyUsage> usageList = Arrays.asList(usage1, usage2);

        when(cycleClient.getLatestCycleHistory(userId, mdn)).thenReturn(cycleResponseDto);
        when(dailyUsageRepository.findByUsageDateBetweenAndUserIdAndMdn(startDate, endDate, userId, mdn)).thenReturn(usageList);

        List<DailyUsage> result = dailyUsageService.getCurrentCycleDailyUsage(userId, mdn);

        assertEquals(2, result.size());
        assertEquals(100, result.get(0).getUsedInMb());
        assertEquals(200, result.get(1).getUsedInMb());


        verify(cycleClient, times(1)).getLatestCycleHistory(userId, mdn);
        verify(dailyUsageRepository, times(1)).findByUsageDateBetweenAndUserIdAndMdn(startDate, endDate, userId, mdn);

    }

    @Test
    void getCurrentCycleDailyUsage_NoLatestCycleFound() {
        String userId = "user123";
        String mdn = "1234567890";

        when(cycleClient.getLatestCycleHistory(userId, mdn)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            dailyUsageService.getCurrentCycleDailyUsage(userId, mdn);
        });

        assertEquals("No Latest Cycle could be found", exception.getMessage());

        verify(cycleClient, times(1)).getLatestCycleHistory(userId, mdn);
        verify(dailyUsageRepository, times(0)).findByUsageDateBetweenAndUserIdAndMdn(any(Date.class), any(Date.class), eq(userId), eq(mdn));
    }
}
