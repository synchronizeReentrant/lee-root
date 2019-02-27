package com.lee.demo.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import com.lee.demo.base.search.Search;
import com.lee.demo.base.search.Searchable;
import lee.demo.po.Account;
import lee.demo.po.User;

import java.util.List;


public interface UserDao extends IBatisDaoWithSingleId<User,String>{

    List<User> findBy(Search search);

//    Account getAccountByUserId(String userId);

    

}
