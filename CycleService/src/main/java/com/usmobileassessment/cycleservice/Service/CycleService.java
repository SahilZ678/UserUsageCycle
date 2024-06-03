package com.usmobileassessment.cycleservice.Service;

import com.usmobileassessment.cycleservice.Dto.CycleResponseDto;
import com.usmobileassessment.cycleservice.Model.Cycle;
import com.usmobileassessment.cycleservice.Repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CycleService {

    @Autowired
    private CycleRepository cycleRepository;

    public List<Cycle> getCyclesByUserId(String userId) {
        return cycleRepository.findByUserId(userId);
    }

    public List<Cycle> getCyclesByMdn(String mdn) {
        return cycleRepository.findByMdn(mdn);
    }

    public List<Cycle> getCycleHistory(String userId, String mdn) {
        return cycleRepository.findByUserIdAndMdn(userId, mdn);
    }

    public Optional<Cycle> getLatestCycle(String userId, String mdn) {
        return getCycleHistory(userId, mdn).stream().max(Comparator.comparing(Cycle::getStartDate));
    }

    public CycleResponseDto convertToDto(Cycle cycle) {
        return CycleResponseDto.builder()
                .id(cycle.getId())
                .startDate(cycle.getStartDate())
                .endDate(cycle.getEndDate())
                .build();
    }
}
