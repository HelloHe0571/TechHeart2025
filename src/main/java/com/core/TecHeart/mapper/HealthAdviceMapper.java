package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.HealthAdvice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthAdviceMapper {
    int insertAdvice(HealthAdvice advice);
    int updateAdvice(HealthAdvice advice);
    int deleteAdvice(Integer adviceId);
    HealthAdvice selectAdviceById(Integer adviceId);
    List<HealthAdvice> selectAllAdvices();
    List<HealthAdvice> selectAdvicesByUserId(Integer userId);
}