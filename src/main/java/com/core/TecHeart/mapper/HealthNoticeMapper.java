package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.HealthNotice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HealthNoticeMapper {
    List<HealthNotice> selectAllNotices();
    List<HealthNotice> getNoticeById(Integer userId);
    int insertNotice(HealthNotice notice);
    int updateNotice(HealthNotice notice);
    int deleteNotice(Integer noticeId);
}
