package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.MedicalRecord;
import com.core.TecHeart.mapper.MedicalRecordMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;


    @Override
    public Result<List<MedicalRecord>> getRecordById(Integer userId) {
        Result<List<MedicalRecord>> result = new Result<>();
        List<MedicalRecord> list = medicalRecordMapper.getRecordById(userId);
        result.setResultSuccess("查询成功",list);
        return result;
    }

    @Override
    public Result<MedicalRecord> createRecord(MedicalRecord record) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        record.setCreatedAt(str);
        record.setUpdatedAt(str);
        Result<MedicalRecord> result = new Result<>();
        int flag = medicalRecordMapper.insertRecord(record);
        if (flag > 0){
            result.setResultSuccess("就诊记录添加成功",record);
        }else {
            result.setResultFailed("就诊记录添加失败");
        }
        return result;
    }

    @Override
    public Result<MedicalRecord> updateRecord(MedicalRecord record) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        record.setUpdatedAt(str);
        Result<MedicalRecord> result = new Result<>();

        int flag = medicalRecordMapper.updateRecord(record);
        if (flag > 0){
            result.setResultSuccess("就诊记录修改成功",record);
        }else {
            result.setResultFailed("就诊记录修改失败");
        }

        return result;
    }

    @Override
    public Result<Void> deleteRecord(Integer recordId) {
        Result<Void> result = new Result<>();
        int flag = medicalRecordMapper.deleteRecord(recordId);
        if (flag > 0){
            result.setResultSuccess("就诊记录删除成功");
        }else{
            result.setResultFailed("就诊记录删除失败");
        }

        return result;
    }
}
