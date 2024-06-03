package com.usmobileassessment.dailyusageservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CycleDto {
    private String id;
    private Date startDate;
    private Date endDate;
    private String mdn;
    private String userId;
}
