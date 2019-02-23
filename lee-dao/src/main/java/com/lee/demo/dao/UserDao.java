package com.lee.demo.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import com.lee.demo.base.search.Search;
import com.lee.demo.base.search.Searchable;
import lee.demo.po.Account;
import lee.demo.po.User;


public interface UserDao extends IBatisDaoWithSingleId<User,String>{

    User findBy(Searchable searchable,boolean filter);

    Account getAccountByUserId(String userId);

}
