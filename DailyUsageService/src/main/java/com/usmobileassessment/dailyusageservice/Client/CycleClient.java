package com.usmobileassessment.dailyusageservice.Client;

import com.usmobileassessment.dailyusageservice.Dto.CycleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties.FeignClientConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "CycleService")
public interface CycleClient {

    @GetMapping("/latest")
    CycleDto getLatestCycleHistory(@RequestParam String userId, @RequestParam String mdn);

    @GetMapping("/")
    List<CycleDto> getAllCycles();
}
