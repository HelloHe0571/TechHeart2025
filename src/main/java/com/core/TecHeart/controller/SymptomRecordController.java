package com.core.TecHeart.controller;

import com.core.TecHeart.entity.SymptomRecord;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.SymptomRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/symptom")
public class SymptomRecordController {

    @Autowired
    private SymptomRecordService symptomRecordService;

    @PostMapping("/create")
    public Result<SymptomRecord> createRecord(@RequestBody SymptomRecord record) {
        return symptomRecordService.createRecord(record);
    }

    @PostMapping("/update")
    public Result<SymptomRecord> updateRecord(@RequestBody SymptomRecord record) {
        return symptomRecordService.updateRecord(record);
    }

    @PostMapping("/delete")
    public Result<Void> deleteRecord(@RequestBody Integer id) {
        return symptomRecordService.deleteRecord(id);
    }

    @GetMapping("/getById")
    public Result<SymptomRecord> getRecordById(@RequestParam Integer id) {
        return symptomRecordService.getRecordById(id);
    }

    @RequestMapping("/getByUser")
    public Result<List<SymptomRecord>> getRecordsByUser(@RequestBody SymptomRecord record) {
        return symptomRecordService.getRecordsByUser(record.getUserId());
    }
}
