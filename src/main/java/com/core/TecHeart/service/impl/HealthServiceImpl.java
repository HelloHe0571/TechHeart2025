package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.*;
import com.core.TecHeart.enums.DataSourceType;
import com.core.TecHeart.mapper.*;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthServiceImpl implements HealthService {

    @Autowired private HealthMetricMapper metricMapper;
    @Autowired private BloodPressureMapper bpMapper;

    @Override
    @Transactional
    public Result<HealthMetric> recordMetric(HealthMetric metric) {
        Result<HealthMetric> result = new Result<>();
        try {
            metric.setDataSource(DataSourceType.MANUAL);
            metric.setCreatedAt(LocalDateTime.now());
            int flag = metricMapper.insert(metric);
            if(flag > 0) {
                result.setResultSuccess("记录保存成功", metric);

            } else {
                result.setResultFailed("记录保存失败");
            }
        } catch (Exception e) {
            result.setResultFailed("系统异常: " + e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional
    public Result<BloodPressure> recordBloodPressure(HealthMetric metric, BloodPressure bp) {
        Result<BloodPressure> result = new Result<>();
        try {
            // 先保存基础指标
            Result<HealthMetric> metricResult = recordMetric(metric);
            if(!metricResult.isSuccess()) {
                result.setResultFailed(metricResult.getMessage());
                return result;
            }

            // 关联血压数据
            bp.setMetricId(metricResult.getData().getMetricId());
            int flag = bpMapper.insert(bp);
            if(flag > 0) {
                result.setResultSuccess("血压记录保存成功", bp);
            } else {
                result.setResultFailed("血压数据保存失败");
            }
        } catch (Exception e) {
            result.setResultFailed("系统异常: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Result<List<HealthMetric>> getMetrics(MetricsRequest metricsRequest) {
        Result<List<HealthMetric>> result = new Result<>();
        LocalDateTime start = LocalDateTime.parse(metricsRequest.getStart());
        LocalDateTime end = LocalDateTime.parse(metricsRequest.getEnd());
        try {
            List<HealthMetric> metrics = new ArrayList<>();
            if (metricsRequest.getType().equals("all")){
                metrics = metricMapper.selectByIdAndTime(metricsRequest.getUserId(), start, end);
            }else{
                metrics = metricMapper.selectByUserAndType(metricsRequest.getUserId(), metricsRequest.getType() ,start, end);
            }


            if(!metrics.isEmpty()) {
                result.setResultSuccess("数据查询成功", metrics);
            } else {
                result.setResultFailed("未找到相关记录");
            }
        } catch (Exception e) {
            result.setResultFailed("查询异常: " + e.getMessage());
        }
        return result;
    }
}
