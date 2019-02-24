package com.lee.demo.dao.impl;

import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.dao.ResourceDao;
import lee.demo.po.Resource;
import org.springframework.stereotype.Repository;

@Repository(value="resourceDao")
public class ResourceDaoImpl extends DefaultBaseDao<Resource,String> implements ResourceDao {
}
