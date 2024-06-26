package com.usmobileassessment.dailyusageservice.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DailyUsageResponseDto {
    Date date;
    Double dailyUsageInMb;
}
