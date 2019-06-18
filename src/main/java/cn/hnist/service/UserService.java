package cn.hnist.service;

import cn.hnist.pojo.Address;
import cn.hnist.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 注册方法
     */
    boolean regist(User user);

    /**
     * 激活用户
     */
    boolean active(String code);

    /**
     * 登录功能
     * 根据用户名和密码查询用户
     */
    User findByNameAndPwd(User user);

    /**
     * 获取用户所有地址
     */
    List<Address> findAllAddress(Integer user_id);
}
