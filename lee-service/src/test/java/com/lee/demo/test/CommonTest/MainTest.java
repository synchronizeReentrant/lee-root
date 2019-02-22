package com.lee.demo.test.CommonTest;

import com.lee.demo.dao.UserDao;
import lee.demo.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-conf/server.xml"})
public class MainTest {
    @Resource
    private UserDao userDao;
    @Test
    public void testJDBCConnection(){
        User user = new User();
        user.setId("1283498123481902348-1");
        user.setIdNumber("4306829895624123644");
        user.setAddress("湖南长沙");
        user.setMobile("1111111111111111");
        user.setName("张三");
        userDao.save(user);


    }
}
