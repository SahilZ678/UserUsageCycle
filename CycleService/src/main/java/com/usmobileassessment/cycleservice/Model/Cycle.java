package com.usmobileassessment.cycleservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "cycle")
@Data
public class Cycle {
    @Id
    private String id;
    private String mdn;
    private Date startDate;
    private Date endDate;
    private String userId;
}
