package com.lee.demo.dao.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lee.demo.base.dao.DefaultBaseDao;
import com.lee.demo.dao.ResourceDao;
import com.lee.demo.dao.RoleDao;
import com.lee.demo.dao.RoleResourceRelatedDao;
import lee.demo.po.Resource;
import lee.demo.po.Role;
import lee.demo.po.RoleResourceRelated;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository(value="resourceDao")
public class ResourceDaoImpl extends DefaultBaseDao<Resource,String> implements ResourceDao {
    @javax.annotation.Resource
    private RoleDao roleDao;

    @javax.annotation.Resource
    private RoleResourceRelatedDao roleResourceRelatedDao;

    @Override
    public Set<Resource> getResourceListByRoles(Set<Role> roleSet) {
        if(roleSet == null || roleSet.isEmpty()) return Sets.newHashSet();
        Set<Resource> resourceSet =Sets.newHashSet();
        RoleResourceRelated roleResourceRelated = new RoleResourceRelated();
        for(Role role : roleSet){
            String roleId = role.getId();
            roleResourceRelated.setId(roleId);
            List<RoleResourceRelated> lists = roleResourceRelatedDao.findByExample(roleResourceRelated);
            for(RoleResourceRelated item : lists){
                Resource resource = getById(item.getResourceId());
                resourceSet.add(resource);
            }
        }
        return resourceSet;
    }

}
