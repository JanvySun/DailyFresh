package cn.hnist.service;

import cn.hnist.pojo.User;

public interface UserService {

    /**
     * 注册方法
     * @param user : 用户
     * @return : 是否注册成功
     */
    boolean regist(User user);

    /**
     * 激活用户
     * @param code : 用户激活码
     * @return : 是否激活成功
     */
    boolean active(String code);

    /**
     * 登录功能
     * 根据用户名和密码查询用户
     * @param user : 用户对象
     * @return : 用户对象
     */
    User findByNameAndPwd(User user);
}
