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
        User user = userDao.getById("1283498123481902348-1");
        System.out.println(user);
    }
}
