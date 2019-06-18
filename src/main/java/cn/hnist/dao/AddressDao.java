package cn.hnist.dao;

import cn.hnist.pojo.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao {

    /**
     * 根据用户id查询其所有地址
     */
    @Select("select * from address where user_id=#{user_id}")
    List<Address> findUserAddress(Integer user_id);

    /**
     * 保存地址信息
     */
    @Insert("insert into address(receiver,addr,zip_code,phone,is_default,user_id) " +
            "values(#{receiver},#{addr},#{zip_code},#{phone},#{is_default},#{user_id})")
    void saveAddress(Address addr);
}
