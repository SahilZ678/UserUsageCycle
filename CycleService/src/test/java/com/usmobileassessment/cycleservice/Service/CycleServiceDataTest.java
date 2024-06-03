package com.usmobileassessment.cycleservice.Service;

import com.usmobileassessment.cycleservice.Configuration.EmbeddedMongoConfig;
import com.usmobileassessment.cycleservice.Model.Cycle;
import com.usmobileassessment.cycleservice.Repository.CycleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import({EmbeddedMongoConfig.class, CycleService.class})
class CycleServiceDataTest {

    @Autowired
    private CycleRepository cycleRepository;


    @Autowired
    private CycleService cycleService;

    @BeforeEach
    void setUp() {
        cycleRepository.deleteAll(); // Clean the database before each test

        // Create and save some mock data
        String userId = "user123";
        String mdn = "1234567890";
        Date startDate1 = new Date();
        Date endDate1 = new Date(startDate1.getTime() + 86400000L); // +1 day
        Date startDate2 = new Date(endDate1.getTime() + 86400000L); // +2 day
        Date endDate2 = new Date(startDate2.getTime() + 86400000L); // +3 day

        Cycle cycle1 = new Cycle();
        cycle1.setMdn(mdn);
        cycle1.setStartDate(startDate1);
        cycle1.setEndDate(endDate1);
        cycle1.setUserId(userId);
        Cycle cycle2 = new Cycle();
        cycle2.setMdn(mdn);
        cycle2.setStartDate(startDate2);
        cycle2.setEndDate(endDate2);
        cycle2.setUserId(userId);

        cycleRepository.save(cycle1);
        cycleRepository.save(cycle2);
    }

    @Test
    void getCycleHistory_Success() {
        List<Cycle> result = cycleService.getCycleHistory("user123", "1234567890");

        assertEquals(2, result.size());
    }

    @Test
    void getLatestCycle_Success() {
        Optional<Cycle> result = cycleService.getLatestCycle("user123", "1234567890");

        assertTrue(result.isPresent());
        assertEquals("user123", result.get().getUserId());
    }
}
