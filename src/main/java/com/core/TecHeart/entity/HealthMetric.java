package com.core.TecHeart.entity;

import com.core.TecHeart.enums.DataSourceType;
import com.core.TecHeart.enums.HealthMetricType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HealthMetric {
    private Long metricId;
    private Integer userId;
    private HealthMetricType metricType;
    private Double metricValue;
    private LocalDateTime recordDate;
    private Integer timeSlot;
    private LocalDateTime createdAt;
    private String deviceSn;
    private DataSourceType dataSource;

    private BloodPressure bloodPressure;
    private Integer systolic;
    private Integer diastolic;

    public Long getMetricId() {
        return metricId;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public HealthMetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(HealthMetricType metricType) {
        this.metricType = metricType;
    }

    public Double getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(Double metricValue) {
        this.metricValue = metricValue;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public DataSourceType getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceType dataSource) {
        this.dataSource = dataSource;
    }

    public BloodPressure getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(BloodPressure bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }
}
