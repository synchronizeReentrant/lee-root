package com.lee.demo.dao.impl;

import com.google.common.base.Strings;
import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.base.search.Operator;
import com.lee.demo.base.search.Search;
import com.lee.demo.dao.AccountDao;
import lee.demo.po.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="accountDao")
public class AccountDaoImpl extends DefaultBaseDao<Account,String> implements AccountDao {
    @Override
    public Account getByUserName(String userName) {
        if(Strings.isNullOrEmpty(userName)){throw new RuntimeException("userName cant't be null");}
        Search search = Search.newSearch();
        search.addSearchFilter(Account.DP.username.name(), Operator.eq,userName);
        List<Account> list = findByExample(search);
        return list == null ? null:(list.size() == 0 ? null : list.get(0));
    }
}
