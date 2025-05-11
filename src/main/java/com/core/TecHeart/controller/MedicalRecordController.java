package com.core.TecHeart.controller;

import com.core.TecHeart.entity.MedicalRecord;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;


    @RequestMapping("/getRecordById")
    public Result<List<MedicalRecord>> getRecordById(@RequestBody Integer userId) {
        return medicalRecordService.getRecordById(userId);
    }

    @RequestMapping("/createRecord")
    public Result<MedicalRecord> createRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.createRecord(record);
    }

    @RequestMapping("/updateRecord")
    public Result<MedicalRecord> updateRecord(@RequestBody MedicalRecord record) {
        return medicalRecordService.updateRecord(record);
    }

    @RequestMapping("/deleteRecord")
    public Result<Void> deleteRecord(@RequestBody Integer recordId) {
        return medicalRecordService.deleteRecord(recordId);
    }
}
