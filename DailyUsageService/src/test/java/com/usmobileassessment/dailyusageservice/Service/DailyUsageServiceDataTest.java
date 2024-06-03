package com.usmobileassessment.dailyusageservice.Service;

import com.usmobileassessment.dailyusageservice.Client.CycleClient;
import com.usmobileassessment.dailyusageservice.Configuration.EmbeddedMongoConfig;
import com.usmobileassessment.dailyusageservice.DailyUsageServiceApplication;
import com.usmobileassessment.dailyusageservice.Dto.CycleDto;
import com.usmobileassessment.dailyusageservice.Model.DailyUsage;
import com.usmobileassessment.dailyusageservice.Repository.DailyUsageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = DailyUsageServiceApplication.class)
@Import({EmbeddedMongoConfig.class, DailyUsageService.class})
class DailyUsageServiceDataTest {

    @Autowired
    private DailyUsageRepository dailyUsageRepository;

    @Autowired
    private DailyUsageService dailyUsageService;

    @MockBean
    private CycleClient cycleClient;

    @BeforeEach
    void setUp() {
        dailyUsageRepository.deleteAll(); // Clean the database before each test

        // Create and save some mock data
        String userId = "user123";
        String mdn = "1234567890";
        Date usageDate = new Date();

        DailyUsage dailyUsage = new DailyUsage();
        dailyUsage.setUsageDate(usageDate);
        dailyUsage.setUserId(userId);
        dailyUsage.setMdn(mdn);
        dailyUsage.setUsedInMb(100.00);

        dailyUsageRepository.save(dailyUsage);

        CycleDto cycleDto = new CycleDto();
        cycleDto.setStartDate(new Date(usageDate.getTime() - 86400000L)); // start date 1 day before usage date
        cycleDto.setEndDate(new Date(usageDate.getTime() + 86400000L)); // end date 1 day after usage date
        Mockito.when(cycleClient.getLatestCycleHistory(userId, mdn)).thenReturn(cycleDto);
    }

    @Test
    void getCurrentCycleDailyUsage_Success() {
        List<DailyUsage> result = dailyUsageService.getCurrentCycleDailyUsage("user123", "1234567890");

        assertEquals(1, result.size());
        assertEquals(100, result.get(0).getUsedInMb());
    }

    @Test
    void getCurrentCycleDailyUsage_NoCycle() {
        Mockito.when(cycleClient.getLatestCycleHistory("user123", "invalid_mdn")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            dailyUsageService.getCurrentCycleDailyUsage("user123", "invalid_mdn");
        });
    }
}
