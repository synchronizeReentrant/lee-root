package com.lee.demo.dao.impl;

import com.google.common.base.Strings;
import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.dao.AccountDao;
import lee.demo.po.Account;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository(value="accountDao")
public class AccountDaoImpl extends DefaultBaseDao<Account,String> implements AccountDao {
    @Override
    public Account getByUserName(String userName) {
        if(Strings.isNullOrEmpty(userName)){throw new RuntimeException("userName cant't be null");}
        Account account = new Account();
        account.setUsername(userName);
        List<Account> list = findByExample(account);
        return list == null ? null:(list.size() == 0 ? null : list.get(0));
    }
}
