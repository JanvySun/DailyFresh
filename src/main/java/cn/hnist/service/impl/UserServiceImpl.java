package cn.hnist.service.impl;

import cn.hnist.dao.UserDao;
import cn.hnist.pojo.User;
import cn.hnist.service.UserService;
import cn.hnist.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public boolean active(String code) {
        boolean is_success = false;
        // 根据激活码查询用户
        User user = userDao.findByCode(code);
        if (user != null) {
            if(user.getStatus()!='Y') {
                // 用户存在且没有激活，更新用户激活码
                userDao.updateStatus(user);
                is_success = true;
            }
        }
        return is_success;
    }

    @Override
    public boolean regist(User user) {
        // 根据用户名查询用户
        User findUser = userDao.findByUsername(user.getUsername());
        if (findUser!=null) {
            // 查到用户，用户名存在，注册失败
            return false;
        } else {
            // 设置激活码和激活状态
            user.setCode(UuidUtil.getUuid());
            user.setStatus('N');
            // 保存用户信息
            userDao.save(user);
            // 发送激活邮件
            // 这里留空，打印邮件中的地址
            String url = "http://localhost/user/active/" + user.getCode();
            System.out.println(url);
            return true;
        }
    }
}
