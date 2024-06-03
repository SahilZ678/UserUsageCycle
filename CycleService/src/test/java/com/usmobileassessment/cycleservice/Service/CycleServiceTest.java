package com.usmobileassessment.cycleservice.Service;

import com.usmobileassessment.cycleservice.Model.Cycle;
import com.usmobileassessment.cycleservice.Repository.CycleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CycleServiceTest {

    @Mock
    private CycleRepository cycleRepository;

    @InjectMocks
    private CycleService cycleService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCycleHistory_Success() {
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

        List<Cycle> cycles = Arrays.asList(cycle1, cycle2);

        when(cycleRepository.findByUserIdAndMdn(userId, mdn)).thenReturn(cycles);

        List<Cycle> result = cycleService.getCycleHistory(userId, mdn);

        assertEquals(2, result.size());
        assertEquals(cycle1, result.get(0));
        assertEquals(cycle2, result.get(1));

        verify(cycleRepository, times(1)).findByUserIdAndMdn(userId, mdn);
    }

    @Test
    void getLatestCycle_Success() {
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

        List<Cycle> cycles = Arrays.asList(cycle1, cycle2);

        when(cycleRepository.findByUserIdAndMdn(userId, mdn)).thenReturn(cycles);

        Optional<Cycle> result = cycleService.getLatestCycle(userId, mdn);

        assertTrue(result.isPresent());
        assertEquals(cycle2, result.get());

        verify(cycleRepository, times(1)).findByUserIdAndMdn(userId, mdn);
    }

    @Test
    void getLatestCycle_NotFound() {
        String userId = "user123";
        String mdn = "1234567890";

        when(cycleRepository.findByUserIdAndMdn(userId, mdn)).thenReturn(List.of());

        Optional<Cycle> result = cycleService.getLatestCycle(userId, mdn);

        assertFalse(result.isPresent());

        verify(cycleRepository, times(1)).findByUserIdAndMdn(userId, mdn);
    }
}
