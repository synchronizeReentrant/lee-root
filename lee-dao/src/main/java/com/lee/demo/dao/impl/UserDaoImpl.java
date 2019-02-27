package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.base.search.Search;
import com.lee.demo.base.search.Searchable;
import com.lee.demo.dao.UserDao;
import lee.demo.po.Account;
import lee.demo.po.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="userDao")
public class UserDaoImpl extends DefaultBaseDao<User,String> implements  UserDao {

    @Override
    public List<User> findBy(Search search) {
        return this.getSqlSession().selectList("com.lee.demo.dao.UserDao.findBy",search);
    }


}
