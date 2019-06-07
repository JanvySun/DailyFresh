package cn.hnist.dao;

import cn.hnist.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username : 用户名
     * @return : 查询到的用户
     */
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    /**
     * 保存用户
     * @param user : 用户对象
     */
    @Insert("insert into user(username,password,is_superuser,email,status,code) " +
            "values(#{username}, #{password}, #{is_superuser}, #{email}, #{status}, #{code})")
    void save(User user);

    /**
     * 根据用户激活码查询用户
     * @param code : 用户激活码
     * @return : 用户对象
     */
    @Select("select * from user where code=#{code}")
    User findByCode(String code);

    /**
     * 更新用户激活码
     * @param user : 用户对象
     */
    @Update("update user set status='Y' where id=#{id}")
    void updateStatus(User user);
}
