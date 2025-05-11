package com.core.TecHeart.service;

import com.core.TecHeart.entity.BloodPressure;
import com.core.TecHeart.entity.HealthMetric;
import com.core.TecHeart.entity.MetricsRequest;
import com.core.TecHeart.model.Result;
import java.time.LocalDateTime;
import java.util.List;

public interface HealthService {
    Result<HealthMetric> recordMetric(HealthMetric metric);
    Result<List<HealthMetric>> getMetrics(MetricsRequest metricsRequest);
    Result<BloodPressure> recordBloodPressure(HealthMetric metric, BloodPressure bp);
}
