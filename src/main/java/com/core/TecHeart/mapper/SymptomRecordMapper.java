package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.SymptomRecord;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SymptomRecordMapper {
    int insertRecord(SymptomRecord record);
    int updateRecord(SymptomRecord record);
    int deleteRecord(Integer id);
    SymptomRecord selectRecordById(Integer id);
    List<SymptomRecord> selectRecordsByUserId(Integer userId);
}
