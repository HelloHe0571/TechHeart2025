package com.core.TecHeart.controller;

import com.core.TecHeart.entity.HealthNotice;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @RequestMapping("/getNoticeById")
    public Result<List<HealthNotice>> getNoticeById(@RequestBody Integer userId) {
        return noticeService.getNoticeById(userId);
    }

    @RequestMapping("/createNotice")
    public Result<HealthNotice> createNotice(@RequestBody HealthNotice notice) {
        return noticeService.createNotice(notice);
    }

    @RequestMapping("/updateNotice")
    public Result<HealthNotice> updateNotice(@RequestBody HealthNotice notice) {
        return noticeService.updateNotice(notice);
    }

    @RequestMapping("/deleteNotice")
    public Result<Void> deleteNotice(@RequestBody Integer noticeId) {
        return noticeService.deleteNotice(noticeId);
    }
}
