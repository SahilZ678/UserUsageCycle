package com.usmobileassessment.cycleservice.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs")
@Data
public class AuditLog {
    @Id
    private String id;
    private String operationType;
    private Object document;
    private LocalDateTime timestamp;
    private String user;
}
