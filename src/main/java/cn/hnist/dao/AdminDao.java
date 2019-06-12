package cn.hnist.dao;

import cn.hnist.pojo.Address;
import cn.hnist.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {

    /**
     * 查询用户表中的所有信息
     */
    @Select("select * from user")
    List<User> findAllUsers();

    /**
     * 查询用户总记录数
     * @return : 数目
     */
    @Select("select count(*) from user")
    Integer findUserTotalCount();

    /**
     * 分页查询每页记录
     * @param start : 开始处
     * @param rows : 数目
     * @return : 结果集
     */
    @Select("select * from user limit #{arg0},#{arg1}")
    List<User> findUserByPage(Integer start, Integer rows);

    /**
     * 根据用户名查询用户
     * @param username : 用户名
     * @return : 查询到的用户
     */
    @Select("select * from user where username=#{username}")
    User findUserByName(String username);

    /**
     * 根据id查询用户
     * @param id : 用户id
     * @return : 用户实体
     */
    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);

    /**
     * 添加用户
     * @param user : 用户实体
     */
    @Insert("insert into user(username,password,is_superuser,email,status,code) " +
            "values(#{username}, #{password}, #{is_superuser}, #{email}, #{status}, #{code})")
    void addUser(User user);

    /**
     * 删除用户
     * @param id : 用户id
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    /**
     * 更新用户信息
     * @param user : 用户实体
     */
    @Update("update user set username=#{username},password=#{password},email=#{email},status=#{status} " +
            "where id=#{id}")
    void updateUser(User user);

    /**
     * 查询某个用户所有的地址
     * @param id : 用户id
     * @return : 结果集
     */
    @Select("select * from address where user_id=#{id}")
    List<Address> findAllAddress(Integer id);

    /**
     * 根据地址id查询地址
     * @param id : 地址id
     * @return : 地址对象
     */
    @Select("select * from address where id=#{id}")
    Address findAddressById(Integer id);

    /**
     * 更新某个用户的所有地址的默认情况为'N'
     * @param user_id : 用户id
     */
    @Update("update address set is_default='N' where user_id=#{user_id}")
    void updateAllAddrDefault(Integer user_id);

    /**
     * 添加地址
     * @param addr : 地址实体
     */
    @Insert("insert into address(receiver,addr,zip_code,phone,is_default,user_id) " +
            "values(#{receiver},#{addr},#{zip_code},#{phone},#{is_default},#{user_id})")
    void addAddress(Address addr);
}
