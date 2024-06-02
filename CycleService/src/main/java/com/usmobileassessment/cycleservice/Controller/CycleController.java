package com.usmobileassessment.cycleservice.Controller;

import com.usmobileassessment.cycleservice.Model.Cycle;
import com.usmobileassessment.cycleservice.Service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/api/cycles")
public class CycleController {
    @Autowired
    private CycleService cycleService;

    @GetMapping("/user/{userId}")
    public List<Cycle> getCyclesByUserId(@PathVariable String userId) {
        return cycleService.getCyclesByUserId(userId);
    }

    @GetMapping("/mdn/{mdn}")
    public List<Cycle> getCyclesByMdn(@PathVariable String mdn) {
        return cycleService.getCyclesByMdn(mdn);
    }
}
