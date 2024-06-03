package com.usmobileassessment.cycleservice.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CycleResponseDto {
    private String id;
    private Date startDate;
    private Date endDate;
}
