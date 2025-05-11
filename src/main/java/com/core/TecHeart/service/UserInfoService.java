package com.core.TecHeart.service;
import com.core.TecHeart.entity.UserInfo;
import com.core.TecHeart.model.Result;

import java.util.List;

public interface UserInfoService {

    Result<UserInfo> createUserInfo(UserInfo userInfo);

    Result<UserInfo> updateUserInfo(UserInfo userInfo);

    Result<UserInfo> getUserInfoByUserId(Integer userId);

    Result<UserInfo> getUserInfoById(Integer userInfoId);

    Result<Void> deleteUserInfo(Integer userInfoId);

    Result<List<UserInfo>> selectAllUser();

    Result<List<UserInfo>> search(String text);
}
