package com.lee.demo.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import lee.demo.po.Account;

import javax.management.relation.Role;
import java.util.List;

public interface AccountDao extends IBatisDaoWithSingleId<Account,String> {

    Account  getByUserName(String userName);

}
