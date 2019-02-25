package com.lee.demo.dao;


import com.lee.demo.base.define.IBatisDaoWithSingleId;
import lee.demo.po.Role;

import java.util.List;

public interface RoleDao extends IBatisDaoWithSingleId<Role,String> {

    List<Role> getRoleListByUserName(String userName);


}
