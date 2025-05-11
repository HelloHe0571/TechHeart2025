package com.core.TecHeart.service;

import com.core.TecHeart.entity.HealthNotice;
import com.core.TecHeart.model.Result;

import java.util.List;

public interface NoticeService {

    Result<List<HealthNotice>> getNoticeById(Integer userId);
    Result<HealthNotice> createNotice(HealthNotice notice);
    Result<HealthNotice> updateNotice(HealthNotice notice);
    Result<Void> deleteNotice(Integer noticeId);
}