package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.BloodPressure;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BloodPressureMapper {
    int insert(BloodPressure bp);
    int update(BloodPressure bp);
    BloodPressure selectByMetricId(Long metricId);
}
