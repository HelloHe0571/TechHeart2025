package com.core.TecHeart.controller;

import com.core.TecHeart.entity.User;
import com.core.TecHeart.entity.UserInfo;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/create")
    public Result<UserInfo> createUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.createUserInfo(userInfo);
    }

    @RequestMapping("/update")
    public Result<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.updateUserInfo(userInfo);
    }

    @RequestMapping("/getUserInfoByUserId")
    public Result<UserInfo> getUserInfoByUserId(@RequestBody Integer userId){
        return userInfoService.getUserInfoByUserId(userId);
    }

    @RequestMapping("/selectAllUser")
    public Result<List<UserInfo>> selectAllUser(){
        return userInfoService.selectAllUser();
    }

    @RequestMapping("/search")
    public Result<List<UserInfo>> search(@RequestBody String text){
        return userInfoService.search(text);
    }

}
