package com.usmobileassessment.cycleservice.Controller;

import com.usmobileassessment.cycleservice.Dto.CycleResponseDto;
import com.usmobileassessment.cycleservice.Model.Cycle;
import com.usmobileassessment.cycleservice.Service.CycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api/cycles")
public class CycleController {
    @Autowired
    private CycleService cycleService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Cycle>> getCyclesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(cycleService.getCyclesByUserId(userId));
    }

    @GetMapping("/mdn/{mdn}")
    public ResponseEntity<List<Cycle>> getCyclesByMdn(@PathVariable String mdn) {
        return ResponseEntity.ok(cycleService.getCyclesByMdn(mdn));
    }

    @GetMapping("/history")
    public ResponseEntity<List<CycleResponseDto>> getCycleHistory(@RequestParam String userId, @RequestParam String mdn) {
        List<Cycle> history = cycleService.getCycleHistory(userId, mdn);
        List<CycleResponseDto> cycleResponseDtoList = history.stream().map(cycle -> cycleService.convertToDto(cycle)).collect(Collectors.toList());
        return ResponseEntity.ok(cycleResponseDtoList);
    }

    @GetMapping("/latest")
    public ResponseEntity<CycleResponseDto> getLatestCycleHistory(@RequestParam String userId, @RequestParam String mdn) {
        Optional<Cycle> latestCycle = cycleService.getLatestCycle(userId, mdn);
        CycleResponseDto cycleResponseDto = latestCycle.map(cycle -> cycleService.convertToDto(cycle)).orElse(null);
        return ResponseEntity.ok(cycleResponseDto);
    }
}
