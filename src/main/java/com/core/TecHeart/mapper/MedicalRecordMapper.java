package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MedicalRecordMapper {
    List<MedicalRecord> selectAllRecords();
    List<MedicalRecord> getRecordById(Integer userId);
    int insertRecord(MedicalRecord record);
    int updateRecord(MedicalRecord record);
    int deleteRecord(Integer recordId);
}