package com.usmobileassessment.dailyusageservice.Controller;

import com.usmobileassessment.dailyusageservice.Dto.DailyUsageResponseDto;
import com.usmobileassessment.dailyusageservice.Model.DailyUsage;
import com.usmobileassessment.dailyusageservice.Service.DailyUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DailyUsageController {
    @Autowired
    private DailyUsageService dailyUsageService;

    @GetMapping("/mdn/{mdn}")
    public ResponseEntity<List<DailyUsage>> getDailyUsageByMdn(@PathVariable String mdn) {
        return ResponseEntity.ok(dailyUsageService.getDailyUsageByMdn(mdn));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DailyUsage>> getDailyUsageByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(dailyUsageService.getDailyUsageByUserId(userId));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<DailyUsage>> getUsageBetweenDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(dailyUsageService.getUsageBetweenDates(startDate, endDate));
    }

    @GetMapping("/current")
    public ResponseEntity<List<DailyUsageResponseDto>> getCurrentCycleDailyUsage(@RequestParam String userId, @RequestParam String mdn) {
        List<DailyUsage> dailyUsagesInCurrentCycle = dailyUsageService.getCurrentCycleDailyUsage(userId, mdn);
        List<DailyUsageResponseDto> dailyUsageResponseDtos = dailyUsagesInCurrentCycle.stream().map(dailyUsage -> dailyUsageService.convertToDto(dailyUsage)).collect(Collectors.toList());
        return ResponseEntity.ok(dailyUsageResponseDtos);
    }
}
