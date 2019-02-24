package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.BaseDaoWithManyId;
import com.lee.demo.dao.AccountRoleRelatedDao;
import lee.demo.po.AccountRoleRelated;
import org.springframework.stereotype.Repository;

@Repository(value="accountRoleRelatedDao")
public  class AccountRoleRelatedDaoImpl extends BaseDaoWithManyId<AccountRoleRelated> implements AccountRoleRelatedDao {
}
