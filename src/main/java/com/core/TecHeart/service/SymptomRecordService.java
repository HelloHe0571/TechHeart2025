package com.core.TecHeart.service;

import com.core.TecHeart.entity.SymptomRecord;
import com.core.TecHeart.model.Result;
import java.util.List;

public interface SymptomRecordService {
    Result<SymptomRecord> createRecord(SymptomRecord record);
    Result<SymptomRecord> updateRecord(SymptomRecord record);
    Result<Void> deleteRecord(Integer id);
    Result<SymptomRecord> getRecordById(Integer id);
    Result<List<SymptomRecord>> getRecordsByUser(Integer userId);
}
