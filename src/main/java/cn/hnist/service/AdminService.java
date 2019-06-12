package cn.hnist.service;

import cn.hnist.pojo.Address;
import cn.hnist.pojo.PageBean;
import cn.hnist.pojo.ResultInfo;
import cn.hnist.pojo.User;

import java.util.List;

public interface AdminService {

    /**
     * 查询所有用户信息
     * @return : 用户信息
     */
    List<User> findAllUsers();

    /**
     * 分页查询用户信息
     * @param currentPage : 当前页码
     * @param rows : 每页显示条数
     * @return : 查询的结果
     */
    PageBean<User> findUserByPage(Integer currentPage, Integer rows);

    /**
     * 添加用户
     * @param user : 用户
     * @return : 保存状态
     */
    ResultInfo saveUser(User user);

    /**
     * 根据用户id删除用户
     * @param id : 用户id
     */
    void deleteUser(Integer id);

    /**
     * 根据用户id查询用户
     * @param id : 用户id
     * @return : 用户实体
     */
    User findUserById(Integer id);

    /**
     * 更新用户
     * @param user : 用户实体
     * @return : 是否更新成功
     */
    ResultInfo updateUser(User user);

    /**
     * 根据多个Id删除用户
     * @param ids : id数组
     */
    void delSelectUser(String[] ids);

    /**
     * 根据用户id查询用户名
     * @param id : 用户id
     * @return : 用户名
     */
    String findUsernameById(Integer id);

    /**
     * 根据用户名查询用户id
     * @param uname : 用户名
     * @return : 用户id
     */
    Integer findUidByUname(String uname);

    /**
     * 根据用户id查询该用户所有地址
     * @param id : 用户id
     * @return : 该用户所有地址
     */
    List<Address> findAllAddress(Integer id);

    /**
     * 根据地址表id查询地址
     * @param id : 地址id
     * @return : 地址结果
     */
    Address findAddressById(Integer id);

    /**
     * 添加地址
     * @param addr : 地址实体
     * @return : 添加结果
     */
    ResultInfo saveAddress(Address addr);
}
