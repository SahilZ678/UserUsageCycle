package com.usmobileassessment.dailyusageservice.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "daily_usage")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyUsage {
    @Id
    private String id;
    private String mdn;
    private String userId;
    private Date usageDate;
    private Double usedInMb;
}
