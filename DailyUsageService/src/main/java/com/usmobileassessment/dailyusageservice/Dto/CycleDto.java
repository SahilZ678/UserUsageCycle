package com.usmobileassessment.dailyusageservice.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CycleDto {
    private String id;
    private Date startDate;
    private Date endDate;
    private String mdn;
    private String userId;
}
