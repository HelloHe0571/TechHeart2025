package com.core.TecHeart.controller;

import com.core.TecHeart.entity.HealthAdvice;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.HealthAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/advice")
public class HealthAdviceController {

    @Autowired
    private HealthAdviceService healthAdviceService;


    @RequestMapping("/createAdvice")
    public Result<HealthAdvice> createAdvice(@RequestBody HealthAdvice advice) {
        return healthAdviceService.createAdvice(advice);
    }

    @RequestMapping("/updateAdvice")
    public Result<HealthAdvice> updateAdvice(@RequestBody HealthAdvice advice) {
        return healthAdviceService.updateAdvice(advice);
    }

    @RequestMapping("/deleteAdvice")
    public Result<Void> deleteAdvice(@RequestBody Integer adviceId) {
        return healthAdviceService.deleteAdvice(adviceId);
    }

    @RequestMapping("/getAdviceById")
    public Result<HealthAdvice> getAdviceById(@RequestBody Integer adviceId) {
        return healthAdviceService.getAdviceById(adviceId);
    }

    @RequestMapping("/getAdvicesByUser")
    public Result<List<HealthAdvice>> getAdvicesByUser(@RequestBody Integer userId) {
        return healthAdviceService.getAdvicesByUserId(userId);
    }
}
