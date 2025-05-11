package com.core.TecHeart.service;

import com.core.TecHeart.entity.HealthAdvice;
import com.core.TecHeart.model.Result;

import java.util.List;

public interface HealthAdviceService {
    Result<HealthAdvice> createAdvice(HealthAdvice advice);
    Result<HealthAdvice> updateAdvice(HealthAdvice advice);
    Result<Void> deleteAdvice(Integer adviceId);
    Result<HealthAdvice> getAdviceById(Integer adviceId);
    Result<List<HealthAdvice>> getAdvicesByUserId(Integer userId);
}
