package cn.hnist.service;

import cn.hnist.pojo.*;

import java.util.List;

public interface AdminService {

    /**
     * 登录
     * @param username : 用户名
     * @param password : 密码
     * @return : 登录结果
     */
    ResultInfo login(String username, String password);

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
     * 根据址表id查询地址
     * @param id : 地址id
     * @return : 地址实体
     */
    Address findAddressById(Integer id);

    /**
     * 添加地址
     * @param addr : 地址实体
     * @return : 添加结果
     */
    ResultInfo saveAddress(Address addr);

    /**
     * 更新地址
     * @param address : 地址实体
     * @return : 更新结果
     */
    ResultInfo updateAddress(Address address);

    /**
     * 删除地址
     * @param id : 地址id
     * @return : 返回该地址的用户id
     */
    Integer deleteAddress(Integer id);

    /**
     * 删除选中的地址
     * @param ids : 地址id集合
     * @return : 这些地址所属的用户
     */
    Integer delSelectAddress(String[] ids);

    /**
     * 查询所有商品类型
     * @return : 结果集
     */
    List<GoodsType> findAllGoodsType();

    /**
     * 根据id查询商品类型
     * @param id : 商品类型id
     * @return : 类型对象
     */
    GoodsType findGoodsTypeById(Integer id);

    /**
     * 更新goods_type表数据
     */
    ResultInfo updateGoodsType(GoodsType goodsType);

    /**
     * 分页查询goods表的数据
     */
    PageBean<Goods> findGoodsByPage(Integer currentPage, Integer rows);

    /**
     * 根据商品ID查询SKU
     * @param id : 商品ID
     * @return : 结果集
     */
    List<GoodsSKU> findAllGoodsSKUByGoodsId(Integer id);

    /**
     * 查询所有SKU
     */
    List<GoodsSKU> findAllGoodsSKU();

    /**
     * 根据商品id查询其名称
     */
    String findGoodsNameById(Integer id);

    /**
     * 通过id查询sku
     */
    GoodsSKU findGoodsSKUById(Integer id);

    /**
     * 更新商品sku
     */
    ResultInfo updateGoodsSKU(GoodsSKU sku);

    /**
     * 查询所有IndexGoodsBanner
     */
    List<IndexGoodsBanner> findAllIndexGoodsBanner();

    /**
     * 根据id查询IndexGoodsBanner
     */
    IndexGoodsBanner findIndexGoodsBannerById(Integer id);

    /**
     * 更新indexbanner
     */
    ResultInfo updateIndexGoodsBanner(IndexGoodsBanner indexGoodsBanner);

    /**
     * 删除indexBanner
     */
    void deleteIndexGoodsBanner(Integer id);

    /**
     * 保存indexBanner
     */
    ResultInfo saveIndexBanner(IndexGoodsBanner banner);

}
