package cn.hnist.service.impl;

import cn.hnist.dao.AddressDao;
import cn.hnist.dao.UserDao;
import cn.hnist.pojo.Address;
import cn.hnist.pojo.User;
import cn.hnist.service.UserService;
import cn.hnist.utils.MailUtils;
import cn.hnist.utils.Md5Util;
import cn.hnist.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AddressDao addressDao;

    @Override
    public boolean regist(User user) {
        // 根据用户名查询用户
        User findUser = userDao.findByUsername(user.getUsername());
        if (findUser != null) {
            // 查到用户，用户名存在，注册失败
            return false;
        } else {
            // 设置激活码和激活状态
            user.setCode(UuidUtil.getUuid());
            user.setStatus('N');
            // 加密密码
            try {
                user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 保存用户信息
            userDao.save(user);
            // 发送激活邮件
            String url = "http://localhost/user/activeHandle/" + user.getCode();
            String to = user.getEmail();
            String title = "天天生鲜欢迎您";
            String text = "<h3>" + user.getUsername() + ",欢迎您成为天天生鲜会员</h3></br>请点击以下链接激活您的账户</br>"
                    + url;
            // 开启新线程异步发送邮件
            new Thread(() -> {
                try {
                    MailUtils.sendMail(to, text, title);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }).start();
            // 打印邮件中的地址
            System.out.println(url);
            return true;
        }
    }

    @Override
    public boolean active(String code) {
        boolean is_success = false;
        // 根据激活码查询用户
        User user = userDao.findByCode(code);
        if (user != null) {
            if (user.getStatus() != 'Y') {
                // 用户存在且没有激活，更新用户激活码
                userDao.updateStatus(user);
                is_success = true;
            }
        }
        return is_success;
    }

    @Override
    public User findByNameAndPwd(User user) {
        // 加密密码
        try {
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDao.findByNameAndPwd(user.getUsername(), user.getPassword());
    }

    @Override
    public List<Address> findAllAddress(Integer user_id) {
        return addressDao.findUserAddress(user_id);
    }

}
