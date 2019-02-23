package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.base.search.Searchable;
import com.lee.demo.dao.UserDao;
import lee.demo.po.Account;
import lee.demo.po.User;
import org.springframework.stereotype.Repository;

@Repository(value="userDao")
public class UserDaoImpl extends DefaultBaseDao<User,String> implements  UserDao {

    @Override
    public User findBy(Searchable searchable, boolean filter) {
        return null;
    }

    @Override
    public Account getAccountByUserId(String userId) {
        return null;
    }
}
