package com.lee.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.lee.demo.dao.UserDao;
import com.lee.demo.service.UserService;
import lee.demo.po.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public String save(String jsonStr) {
        User user= JSON.parseObject(jsonStr, User.class);
        Integer i = userDao.save(user);
        return JSON.toJSONString(i);
    }

    @Override
    public String update(String jsonStr) {
        return null;
    }

    @Override
    public String delete(String jsonStr) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public String findBy(String str) {
        return null;
    }
}
