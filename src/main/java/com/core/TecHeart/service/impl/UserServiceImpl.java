package com.core.TecHeart.service.impl;

import com.core.TecHeart.controller.UserController;
import com.core.TecHeart.entity.User;
import com.core.TecHeart.mapper.UserMapper;
import com.core.TecHeart.model.Result;
import com.core.TecHeart.service.UserService;
import com.core.TecHeart.util.ClassExamine;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<User> register(User user) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        user.setTime(str);

        Result<User> result = new Result<>();
        // 先去数据库找用户名是否存在
        User getUser = userMapper.getByUsername(user.getUsername());
        if (getUser != null) {

            result.setResultFailed("该用户名已存在！");
            return result;
        }
        // 加密储存用户的密码
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        // 存入数据库
        userMapper.add(user);
        // 返回成功消息
        result.setResultSuccess("注册用户成功！", user);
        return result;
    }

    @Override
    public Result<User> login(User user) {

        Result<User> result = new Result<>();
        // 去数据库查找用户
        User getUser = userMapper.getByUsername(user.getUsername());
        if (getUser == null) {

            result.setResultFailed("用户不存在！");
            return result;
        }
        // 比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {

            result.setResultFailed("用户名或者密码错误！");
            return result;
        }
        // 设定登录成功消息
        result.setResultSuccess("登录成功！", getUser);
        return result;
    }

    @Override
    public Result<User> update(User user) throws Exception {

        Result<User> result = new Result<>();
        // 去数据库查找用户
        User getUser = userMapper.getById(user.getId());
        if (getUser == null) {

            result.setResultFailed("用户不存在！");
            return result;
        }
        // 检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
        if (!StringUtils.isEmpty(user.getPassword())) {

            // 加密储存
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }
        // 对象互补
        ClassExamine.objectOverlap(user, getUser);
        // 存入数据库
        userMapper.update(user);
        result.setResultSuccess("修改用户成功！", user);
        return result;
    }

    @Override
    public Result<User> isLogin(HttpSession session) {

        Result<User> result = new Result<>();
        // 从session中取出用户信息
        User sessionUser = (User) session.getAttribute(UserController.SESSION_NAME);
        // 若session中没有用户信息这说明用户未登录
        if (sessionUser == null) {

            result.setResultFailed("用户未登录！");
            return result;
        }
        // 登录了则去数据库取出信息进行比对
        User getUser = userMapper.getById(sessionUser.getId());
        // 如果session用户找不到对应的数据库中的用户或者找出的用户密码和session中用户不一致则说明session中用户信息无效
        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {

            result.setResultFailed("用户信息无效！");
            return result;
        }
        result.setResultSuccess("用户已登录！", getUser);
        return result;
    }

    @Override
    public Result<Void> deleteById(Integer id) {
        Result<Void> result = new Result<>();
        int flag = userMapper.deleteById(id);
        if (flag > 0){
            result.setResultSuccess("删除成功");
        }else {
            result.setResultFailed("用户信息不存在或删除失败");
        }

        return result;
    }
}
