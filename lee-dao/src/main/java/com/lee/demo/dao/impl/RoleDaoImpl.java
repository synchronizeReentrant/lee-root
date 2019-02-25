package com.lee.demo.dao.impl;

import com.google.common.collect.Lists;
import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.dao.AccountDao;
import com.lee.demo.dao.AccountRoleRelatedDao;
import com.lee.demo.dao.RoleDao;
import lee.demo.po.Account;
import lee.demo.po.AccountRoleRelated;
import lee.demo.po.Role;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository(value="roleDao")
public class RoleDaoImpl extends DefaultBaseDao<Role,String> implements RoleDao {
    @Resource
    private AccountRoleRelatedDao accountRoleRelatedDao;

    @Resource
    private AccountDao accountDao;

    @Override
    public List<Role> getRoleListByUserName(String userName) {
        List<Role> roleList = Lists.newArrayList();
        Account account = accountDao.getByUserName(userName);
        AccountRoleRelated accountRoleRelated = new AccountRoleRelated();
        accountRoleRelated.setAccountId(account.getId());
        List<AccountRoleRelated> list = accountRoleRelatedDao.findByExample(accountRoleRelated);
        for(AccountRoleRelated item : list){
             Role role = getById(item.getRoleId());
             roleList.add(role);
        }
        return roleList;
    }

}
