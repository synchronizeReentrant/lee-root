package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.base.define.IBatisWithBase;
import com.lee.demo.dao.UserDao;
import lee.demo.po.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends DefaultBaseDao<User,String> implements IBatisWithBase<User>, UserDao {

}
