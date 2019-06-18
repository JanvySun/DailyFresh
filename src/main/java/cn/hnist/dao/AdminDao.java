package cn.hnist.dao;

import cn.hnist.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {

    /**
     * 查询用户表中的所有信息
     */
    @Select("select * from user where is_delete='N'")
    List<User> findAllUsers();

    /**
     * 查询用户总记录数
     *
     * @return : 数目
     */
    @Select("select count(1) from user where is_delete='N'")
    Integer findUserTotalCount();

    /**
     * 分页查询每页记录
     *
     * @param start : 开始处
     * @param rows  : 数目
     * @return : 结果集
     */
    @Select("select * from user where is_delete='N' limit #{arg0},#{arg1}")
    List<User> findUserByPage(Integer start, Integer rows);

    /**
     * 根据用户名查询用户
     *
     * @param username : 用户名
     * @return : 查询到的用户
     */
    @Select("select * from user where username=#{username}")
    User findUserByName(String username);

    /**
     * 根据id查询用户
     *
     * @param id : 用户id
     * @return : 用户实体
     */
    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);

    /**
     * 通过用户名和密码查询用户
     *
     * @param username : 用户名
     * @param password : 密码
     * @return : 查询到的用户对象
     */
    @Select("select * from user where username=#{arg0} and password=#{arg1};")
    User findUserByNameAndPwd(String username, String password);

    /**
     * 添加用户
     *
     * @param user : 用户实体
     */
    @Insert("insert into user(username,password,is_superuser,email,status,code) " +
            "values(#{username}, #{password}, #{is_superuser}, #{email}, #{status}, #{code})")
    void addUser(User user);

    /**
     * 删除用户
     *
     * @param id : 用户id
     */
    @Delete("update user set is_delete='Y' where id=#{id}")
    void deleteUser(Integer id);

    /**
     * 更新用户信息
     *
     * @param user : 用户实体
     */
    @Update("update user set username=#{username},password=#{password},email=#{email},status=#{status} " +
            "where id=#{id}")
    void updateUser(User user);

    /**
     * 查询某个用户所有的地址
     *
     * @param id : 用户id
     * @return : 结果集
     */
    @Select("select * from address where user_id=#{id}")
    List<Address> findAllAddress(Integer id);

    /**
     * 根据地址id查询地址
     *
     * @param id : 地址id
     * @return : 地址对象
     */
    @Select("select * from address where id=#{id}")
    Address findAddressById(Integer id);

    /**
     * 更新某个用户的所有地址的默认情况为'N'
     *
     * @param user_id : 用户id
     */
    @Update("update address set is_default='N' where user_id=#{user_id}")
    void updateAllAddrDefault(Integer user_id);

    /**
     * 添加地址
     *
     * @param addr : 地址实体
     */
    @Insert("insert into address(receiver,addr,zip_code,phone,is_default,user_id) " +
            "values(#{receiver},#{addr},#{zip_code},#{phone},#{is_default},#{user_id})")
    void addAddress(Address addr);

    /**
     * 更新地址
     *
     * @param address : 地址实体
     */
    @Update("update address set receiver=#{receiver},addr=#{addr},zip_code=#{zip_code},phone=#{phone}," +
            "is_default=#{is_default},user_id=#{user_id} where id=#{id}")
    void updateAddress(Address address);

    /**
     * 删除地址
     *
     * @param id : 地址id
     */
    @Delete("delete from address where id=#{id}")
    void deleteAddress(Integer id);

    /**
     * 查询 goods_type表中的所有元素
     *
     * @return : 结果集
     */
    @Select("select * from goods_type")
    List<GoodsType> findAllGoodsType();

    /**
     * 根据id查询goods_type表
     */
    @Select("select * from goods_type where id=#{id}")
    GoodsType findGoodsTypeById(Integer id);

    /**
     * 更新goods_type表中记录
     */
    @Update("update goods_type set image=#{image} where id=#{id}")
    void updateGoodsType(GoodsType goodsType);

    /**
     * 分页查询goods表
     */
    @Select("select * from goods limit #{arg0},#{arg1}")
    List<Goods> findGoodsByPage(int start, Integer rows);

    /**
     * 查询goods 表总记录数
     */
    @Select("select count(*) from goods")
    int findGoosdTotalCount();

    /**
     * 根据id查询goods
     */
    @Select("select * from goods where id=#{id}")
    Goods findGoodsById(Integer id);

    /**
     * 根据商品id查询其所有SKU
     */
    @Select("select * from goods_sku where goods_id=#{id}")
    List<GoodsSKU> findAllGoodsSKUByGoodsId(Integer id);

    /**
     * 查询所有SKU
     */
    @Select("select * from goods_sku")
    List<GoodsSKU> findAllGoodsSKU();

    /**
     * 根据id查询sku
     */
    @Select("select * from goods_sku where id=#{id}")
    GoodsSKU findGoodsSkuById(Integer id);

    /**
     * 更新sku
     */
    @Update("update goods_sku set name=#{name},`desc`=#{desc},price=#{price},unite=#{unite}," +
            "image=#{image},stock=#{stock},sales=#{sales},status=#{status} where id=#{id}")
    void updateGoodsSKU(GoodsSKU sku);

    /**
     * 查询index_banner表所有信息
     */
    @Select("select * from index_banner")
    List<IndexGoodsBanner> findAllIndexGoodsBanner();

    /**
     * 根据id查index_banner表内容
     */
    @Select("select * from index_banner where id=#{id}")
    IndexGoodsBanner findIndexGoodsBannerById(Integer id);

    /**
     * 更新index_banner表内容
     */
    @Select("update index_banner set image=#{image},od=#{od} where id=#{id}")
    void updateIndexGoodsBanner(IndexGoodsBanner indexGoodsBanner);

    /**
     * 删除index_banner
     */
    @Delete("delete from index_banner where id=#{id}")
    void deleteIndexGoodsBanner(Integer id);

    /**
     * 添加index_banner
     */
    @Insert("insert into index_banner(image, od, sku_id) VALUES(#{image},#{od},#{sku_id})")
    void addIndexGoodsBanner(IndexGoodsBanner banner);
}
