package com.core.TecHeart.mapper;

import com.core.TecHeart.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    int insert(UserInfo userInfo);

    int update(UserInfo userInfo);

    int deleteById(Integer userInfoId);

    UserInfo selectById(Integer userInfoId);

    UserInfo selectByUserId(Integer userId);

    List<UserInfo> selectAllUser();

    List<UserInfo> search(String text);
}
