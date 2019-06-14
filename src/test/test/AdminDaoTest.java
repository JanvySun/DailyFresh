package test;

import cn.hnist.dao.AdminDao;
import cn.hnist.pojo.Address;
import cn.hnist.pojo.GoodsType;
import cn.hnist.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AdminDaoTest {

    @Autowired
    AdminDao adminDao;

    @Test
    public void test01() {
        List<User> users = adminDao.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test02() {
        List<User> users = adminDao.findUserByPage(0, 5);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test03() {
        Integer userTotalCount = adminDao.findUserTotalCount();
        System.out.println(userTotalCount);
    }

    @Test
    public void test04() {
        adminDao.deleteUser(5);
    }

    @Test
    public void test05() {
        User user = adminDao.findUserById(3);
        System.out.println(user);
    }

    @Test
    public void test06() {
        User user = adminDao.findUserById(7);
        user.setStatus('Y');
        adminDao.updateUser(user);
    }

    @Test
    public void test07() {
        List<Address> list = adminDao.findAllAddress(3);
        for (Address address : list) {
            System.out.println(address);
        }
    }

    @Test
    public void test08() {
        List<GoodsType> allGoodsType = adminDao.findAllGoodsType();
        for (GoodsType type : allGoodsType) {
            System.out.println(type);
        }
    }

}
