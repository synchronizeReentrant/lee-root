package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.dao.OrganizationDao;
import lee.demo.po.Organization;
import org.springframework.stereotype.Repository;

@Repository(value="organizationDao")
public class OrganizationDaoImpl extends DefaultBaseDao<Organization,String> implements OrganizationDao {

}
