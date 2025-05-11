package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.HealthNotice;
import com.core.TecHeart.entity.UserInfo;
import com.core.TecHeart.mapper.HealthNoticeMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private HealthNoticeMapper healthNoticeMapper;


    @Override
    public Result<List<HealthNotice>> getNoticeById(Integer userId) {
        Result<List<HealthNotice>> result = new Result<>();
        List<HealthNotice> list = healthNoticeMapper.getNoticeById(userId);
        result.setResultSuccess("查询成功",list);
        return result;
    }

    @Override
    public Result<HealthNotice> createNotice(HealthNotice notice) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        notice.setCreatedAt(str);
        notice.setUpdatedAt(str);
        Result<HealthNotice> result = new Result<>();
        int flag = healthNoticeMapper.insertNotice(notice);
        if (flag > 0){
            result.setResultSuccess("通知添加成功",notice);
        }else {
            result.setResultFailed("通知添加失败");
        }
        return result;
    }

    @Override
    public Result<HealthNotice> updateNotice(HealthNotice notice) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);

        notice.setUpdatedAt(str);
        Result<HealthNotice> result = new Result<>();

        int flag = healthNoticeMapper.updateNotice(notice);
        if (flag > 0){
            result.setResultSuccess("通知修改成功",notice);
        }else {
            result.setResultFailed("通知修改失败");
        }

        return result;
    }

    @Override
    public Result<Void> deleteNotice(Integer noticeId) {
        Result<Void> result = new Result<>();
        int flag = healthNoticeMapper.deleteNotice(noticeId);
        if (flag > 0){
            result.setResultSuccess("通知删除成功");
        }else{
            result.setResultFailed("通知删除失败");
        }

        return result;
    }
}