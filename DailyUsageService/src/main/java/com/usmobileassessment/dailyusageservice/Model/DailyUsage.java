package com.usmobileassessment.dailyusageservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "daily_usage")
@Data
public class DailyUsage {
    @Id
    private String id;
    private String mdn;
    private String userId;
    private Date usageDate;
    private Double usedInMb;
}
