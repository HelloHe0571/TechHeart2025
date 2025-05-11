package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.SymptomRecord;
import com.core.TecHeart.mapper.SymptomRecordMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.SymptomRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SymptomRecordServiceImpl implements SymptomRecordService {

    @Autowired
    private SymptomRecordMapper symptomRecordMapper;

    @Override
    public Result<SymptomRecord> createRecord(SymptomRecord record) {
        Result<SymptomRecord> result = new Result<>();
        record.setRecordTime(LocalDateTime.now());
        int flag = symptomRecordMapper.insertRecord(record);
        if (flag > 0) {
            result.setResultSuccess("症状记录添加成功", record);
        } else {
            result.setResultFailed("症状记录添加失败");
        }
        return result;
    }

    @Override
    public Result<SymptomRecord> updateRecord(SymptomRecord record) {
        Result<SymptomRecord> result = new Result<>();
        int flag = symptomRecordMapper.updateRecord(record);
        if (flag > 0) {
            result.setResultSuccess("症状记录更新成功", record);
        } else {
            result.setResultFailed("症状记录更新失败");
        }
        return result;
    }

    @Override
    public Result<Void> deleteRecord(Integer id) {
        Result<Void> result = new Result<>();
        int flag = symptomRecordMapper.deleteRecord(id);
        if (flag > 0) {
            result.setResultSuccess("症状记录删除成功");
        } else {
            result.setResultFailed("症状记录删除失败");
        }
        return result;
    }

    @Override
    public Result<SymptomRecord> getRecordById(Integer id) {
        Result<SymptomRecord> result = new Result<>();
        SymptomRecord record = symptomRecordMapper.selectRecordById(id);
        if (record != null) {
            result.setResultSuccess("查询成功", record);
        } else {
            result.setResultFailed("记录不存在");
        }
        return result;
    }

    @Override
    public Result<List<SymptomRecord>> getRecordsByUser(Integer userId) {
        Result<List<SymptomRecord>> result = new Result<>();
        List<SymptomRecord> records = symptomRecordMapper.selectRecordsByUserId(userId);
        result.setResultSuccess("查询成功", records);
        return result;
    }
}
