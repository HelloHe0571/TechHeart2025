package com.core.TecHeart.service.impl;

import com.core.TecHeart.entity.UserInfo;
import com.core.TecHeart.mapper.UserInfoMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Result<UserInfo> createUserInfo(UserInfo userInfo) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        userInfo.setCreatedAt(str);
        userInfo.setUpdatedAt(str);

        Result<UserInfo> result = new Result<>();
        int flag = userInfoMapper.insert(userInfo);
        if (flag > 0){
            result.setResultSuccess("用户信息添加成功",userInfo);
        }else {
            result.setResultFailed("用户信息添加失败");
        }
        return result;
    }

    @Override
    public Result<UserInfo> updateUserInfo(UserInfo userInfo) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        userInfo.setUpdatedAt(str);

        Result<UserInfo> result = new Result<>();
        Result<UserInfo> find = this.getUserInfoByUserId(userInfo.getUserId());

        if (find.isSuccess()){
            int flag = userInfoMapper.update(userInfo);
            if (flag > 0){
                result.setResultSuccess("用户信息修改成功",userInfo);
            }else {
                result.setResultFailed("用户信息修改失败");
            }
        }else {
            result =  this.createUserInfo(userInfo);
        }

        return result;

    }

    @Override
    public Result<UserInfo> getUserInfoByUserId(Integer userId) {
        Result<UserInfo> result = new Result<>();
        UserInfo userInfo = userInfoMapper.selectByUserId(userId);
        if (userInfo != null){
            result.setResultSuccess("查询成功",userInfo);
        }else {
            result.setResultFailed("查询失败");
        }
        return result;
    }

    @Override
    public Result<UserInfo> getUserInfoById(Integer userInfoId) {
        Result<UserInfo> result = new Result<>();
        UserInfo userInfo = userInfoMapper.selectById(userInfoId);
        if (userInfo != null){
            result.setResultSuccess("查询成功",userInfo);
        }else {
            result.setResultFailed("查询失败");
        }
        return result;
    }


    @Override
    public Result<Void> deleteUserInfo(Integer userInfoId) {
        Result<Void> result = new Result<>();
        int flag = userInfoMapper.deleteById(userInfoId);
        if (flag > 0){
            result.setResultSuccess("删除成功");
        }else {
            result.setResultFailed("用户信息不存在或删除失败");
        }

        return result;
    }

    @Override
    public Result<List<UserInfo>> selectAllUser(){
        Result<List<UserInfo>> result = new Result<>();
        List<UserInfo> list = userInfoMapper.selectAllUser();
        result.setResultSuccess("查询成功",list);

        return result;
    }

    @Override
    public Result<List<UserInfo>> search(String text){
        Result<List<UserInfo>> result = new Result<>();
        List<UserInfo> list = userInfoMapper.search(text);
        result.setResultSuccess("查询成功",list);

        return result;
    }

}
