package com.usmobileassessment.dailyusageservice.Controller;

import com.usmobileassessment.dailyusageservice.Model.DailyUsage;
import com.usmobileassessment.dailyusageservice.Service.DailyUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class DailyUsageController {
    @Autowired
    private DailyUsageService dailyUsageService;

    @GetMapping("/mdn/{mdn}")
    public List<DailyUsage> getDailyUsageByMdn(@PathVariable String mdn) {
        return dailyUsageService.getDailyUsageByMdn(mdn);
    }

    @GetMapping("/user/{userId}")
    public List<DailyUsage> getDailyUsageByUserId(@PathVariable String userId) {
        return dailyUsageService.getDailyUsageByUserId(userId);
    }

    @GetMapping("/date-range")
    public List<DailyUsage> getUsageBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return dailyUsageService.getUsageBetweenDates(startDate, endDate);
    }
}
