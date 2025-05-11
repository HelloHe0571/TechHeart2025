package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.HealthMetric;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface HealthMetricMapper {
    int insert(HealthMetric metric);
    int update(HealthMetric metric);
    int delete(Long metricId);
    List<HealthMetric> selectByIdAndTime(Integer userId,@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end);
    List<HealthMetric> selectByUserAndType(
            @Param("userId") Integer userId,
            @Param("metricType") String metricType,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
