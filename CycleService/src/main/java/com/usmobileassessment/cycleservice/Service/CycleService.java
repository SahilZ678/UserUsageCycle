package com.usmobileassessment.cycleservice.Service;

import com.usmobileassessment.cycleservice.Model.Cycle;
import com.usmobileassessment.cycleservice.Repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
