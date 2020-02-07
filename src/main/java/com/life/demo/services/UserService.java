package com.life.demo.services;

import com.life.demo.Model.User;
import com.life.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser =userMapper.findByaccountId(user.getAccountId());
        if(dbuser==null){
            //创造并插入数据库
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.Insert(user);
        }else {
            //更新数据库
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setToken(user.getToken());
            dbuser.setName(user.getName());
            userMapper.update(dbuser);
        }
    }
}
