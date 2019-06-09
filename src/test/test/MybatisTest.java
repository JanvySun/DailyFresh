package test;

import cn.hnist.dao.AddressDao;
import cn.hnist.dao.UserDao;
import cn.hnist.pojo.Address;
import cn.hnist.pojo.User;
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
    private AddressDao addressDao;

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

    @Test
    public void test4() {
        List<Address> list = addressDao.findUserAddress(3);
        for (Address address : list) {
            System.out.println(address);
        }
    }

    @Test
    public void test5() {
        Address a = new Address();
        a.setReceiver("李四");
        a.setPhone("15624523522");
        a.setZip_code("543210");
        a.setIs_default('Y');
        a.setAddr("岳阳市岳阳楼区湖南理工学院南院后门");
        a.setUser_id(3);
        addressDao.saveAddress(a);
    }

}
