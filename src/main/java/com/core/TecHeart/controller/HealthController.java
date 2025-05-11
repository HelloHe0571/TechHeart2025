package com.core.TecHeart.controller;

import com.core.TecHeart.entity.BloodPressure;
import com.core.TecHeart.entity.HealthMetric;
import com.core.TecHeart.entity.MetricsRequest;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired private HealthService healthService;

    @PostMapping("/metric")
    public Result<HealthMetric> recordMetric(@RequestBody HealthMetric metric) {
        return healthService.recordMetric(metric);
    }

    @PostMapping("/blood-pressure")
    public Result<BloodPressure> recordBP(@RequestBody HealthMetric metric) {
        BloodPressure bp = metric.getBloodPressure();
        return healthService.recordBloodPressure(metric, bp);
    }

    @RequestMapping("/getMetrics")
    public Result<List<HealthMetric>> getMetrics(@RequestBody MetricsRequest metricsRequest) {
        return healthService.getMetrics(metricsRequest);
    }
}