package cn.hnist.service;

import cn.hnist.pojo.*;

import java.util.List;

public interface AdminService {

    /**
     * 登录
     */
    ResultInfo login(String username, String password);

    /**
     * 查询所有用户信息
     */
    List<User> findAllUsers();

    /**
     * 分页查询用户信息
     */
    PageBean<User> findUserByPage(Integer currentPage, Integer rows);

    /**
     * 添加用户
     */
    ResultInfo saveUser(User user);

    /**
     * 根据用户id删除用户
     */
    void deleteUser(Integer id);

    /**
     * 根据用户id查询用户
     */
    User findUserById(Integer id);

    /**
     * 更新用户
     */
    ResultInfo updateUser(User user);

    /**
     * 根据多个Id删除用户
     */
    void delSelectUser(String[] ids);

    /**
     * 根据用户id查询用户名
     */
    String findUsernameById(Integer id);

    /**
     * 根据用户名查询用户id
     */
    Integer findUidByUname(String uname);

    /**
     * 根据用户id查询该用户所有地址
     */
    List<Address> findAllAddress(Integer id);

    /**
     * 根据址表id查询地址
     */
    Address findAddressById(Integer id);

    /**
     * 添加地址
     */
    ResultInfo saveAddress(Address addr);

    /**
     * 更新地址
     */
    ResultInfo updateAddress(Address address);

    /**
     * 删除地址
     */
    Integer deleteAddress(Integer id);

    /**
     * 删除选中的地址
     */
    Integer delSelectAddress(String[] ids);

    /**
     * 查询所有商品类型
     */
    List<GoodsType> findAllGoodsType();

    /**
     * 根据id查询商品类型
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
