package com.usmobileassessment.dailyusageservice.Client;

import com.usmobileassessment.dailyusageservice.Dto.CycleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CycleService")
public interface CycleClient {

    @GetMapping("/latest")
    CycleDto getLatestCycleHistory(@RequestParam String userId, @RequestParam String mdn);
}
