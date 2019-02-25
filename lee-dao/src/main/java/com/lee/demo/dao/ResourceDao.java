package com.lee.demo.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import lee.demo.po.Resource;
import lee.demo.po.Role;

import java.util.List;
import java.util.Set;

public interface ResourceDao extends IBatisDaoWithSingleId<Resource,String> {


    Set<Resource> getResourceListByRoles(Set<Role> role);

}
