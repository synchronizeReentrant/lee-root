package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.dao.AccountDao;
import lee.demo.po.Account;
import org.springframework.stereotype.Repository;

@Repository(value="accountDao")
public class AccountDaoImpl extends DefaultBaseDao<Account,String> implements AccountDao {
}
