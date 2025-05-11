package com.core.TecHeart.service;

import com.core.TecHeart.entity.MedicalRecord;
import com.core.TecHeart.model.Result;

import java.util.List;

public interface MedicalRecordService {
    Result<List<MedicalRecord>> getRecordById(Integer userId);
    Result<MedicalRecord> createRecord(MedicalRecord record);
    Result<MedicalRecord> updateRecord(MedicalRecord record);
    Result<Void> deleteRecord(Integer recordId);
}
