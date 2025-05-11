package com.core.TecHeart.entity;

import java.time.LocalDateTime;

public class SymptomRecord {
    private Integer id;
    private Integer userId;
    private LocalDateTime recordTime;
    private String symptomType;
    private Integer severity;
    private String description;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public LocalDateTime getRecordTime() { return recordTime; }
    public void setRecordTime(LocalDateTime recordTime) { this.recordTime = recordTime; }

    public String getSymptomType() { return symptomType; }
    public void setSymptomType(String symptomType) { this.symptomType = symptomType; }

    public Integer getSeverity() { return severity; }
    public void setSeverity(Integer severity) { this.severity = severity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
