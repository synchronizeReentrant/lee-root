package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.BaseDaoWithManyId;
import com.lee.demo.dao.RoleResourceRelatedDao;
import lee.demo.po.RoleResourceRelated;
import org.springframework.stereotype.Repository;

@Repository(value="roleResourceRelatedDao")
public class RoleResourceRelatedDaoImpl extends BaseDaoWithManyId<RoleResourceRelated> implements RoleResourceRelatedDao {
}
