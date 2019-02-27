package com.lee.demo.test.CommonTest;

import com.lee.demo.base.search.Operator;
import com.lee.demo.base.search.Search;
import com.lee.demo.dao.UserDao;
import lee.demo.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

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

    @Test
    public void testJDBCConnection1(){
        Search search = Search.newSearch();
        search.addSearchFilter(User.DP.id.name(), Operator.eq,"1283498123481902348-1");
        System.out.println("---------->"+search.getWhereSqlCustom());
        List<User> list= userDao.findBy(search);
        System.out.println(list);
    }
}
