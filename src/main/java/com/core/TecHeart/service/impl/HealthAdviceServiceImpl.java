package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.HealthAdvice;
import com.core.TecHeart.mapper.HealthAdviceMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.HealthAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class HealthAdviceServiceImpl implements HealthAdviceService {

    @Autowired
    private HealthAdviceMapper healthAdviceMapper;


    @Override
    public Result<HealthAdvice> createAdvice(HealthAdvice advice) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        advice.setCreatedAt(str);
        advice.setUpdatedAt(str);
        Result<HealthAdvice> result = new Result<>();
        int flag = healthAdviceMapper.insertAdvice(advice);
        if (flag > 0){
            result.setResultSuccess("今日健康建议添加成功",advice);
        }else {
            result.setResultFailed("今日健康建议添加失败");
        }
        return result;
    }

    @Override
    public Result<HealthAdvice> updateAdvice(HealthAdvice advice) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        advice.setUpdatedAt(str);
        Result<HealthAdvice> result = new Result<>();

        int flag = healthAdviceMapper.updateAdvice(advice);
        if (flag > 0){
            result.setResultSuccess("今日健康建议修改成功",advice);
        }else {
            result.setResultFailed("今日健康建议修改失败");
        }

        return result;
    }

    @Override
    public Result<Void> deleteAdvice(Integer adviceId) {
        Result<Void> result = new Result<>();
        int flag = healthAdviceMapper.deleteAdvice(adviceId);
        if (flag > 0){
            result.setResultSuccess("今日健康建议删除成功");
        }else{
            result.setResultFailed("今日健康建议删除失败");
        }

        return result;
    }

    @Override
    public Result<HealthAdvice> getAdviceById(Integer adviceId) {
        Result<HealthAdvice> result = new Result<>();
        HealthAdvice healthAdvice = healthAdviceMapper.selectAdviceById(adviceId);
        if (healthAdvice != null){
            result.setResultSuccess("查询成功",healthAdvice);
        }else {
            result.setResultFailed("查询失败");
        }

        return result;
    }

    @Override
    public Result<List<HealthAdvice>> getAdvicesByUserId(Integer userId) {
        Result<List<HealthAdvice>> result = new Result<>();
        List<HealthAdvice> list = healthAdviceMapper.selectAdvicesByUserId(userId);
        result.setResultSuccess("查询成功",list);
        return result;
    }
}
