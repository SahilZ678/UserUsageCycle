package com.usmobileassessment.userservice.Service;

import com.usmobileassessment.userservice.Model.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void log(String operationType, Object document, String user) {
        AuditLog auditLog = new AuditLog();
        auditLog.setOperationType(operationType);
        auditLog.setDocument(document);
        auditLog.setTimestamp(LocalDateTime.now());
        auditLog.setUser(user);

        mongoTemplate.save(auditLog);
    }
}
