package test;

import cn.hnist.dao.UserDao;
import cn.hnist.pojo.User;
import cn.hnist.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        List<User> users = userDao.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        User user = userDao.findByUsername("hello");
        System.out.println(user);
    }

    @Test
    public void test3() {
        User user = userDao.findByNameAndPwd("hello", "12345");
        System.out.println(user);
    }

}
