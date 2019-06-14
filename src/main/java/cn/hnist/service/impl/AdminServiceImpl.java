package cn.hnist.service.impl;

import cn.hnist.dao.AdminDao;
import cn.hnist.pojo.*;
import cn.hnist.service.AdminService;
import cn.hnist.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public ResultInfo login(String username, String password) {
        ResultInfo info = new ResultInfo();
        User user = adminDao.findUserByNameAndPwd(username, password);
        if(user==null){
            info.setFlag(false);
            info.setMessage("用户名或密码错误");
        } else {
            if(user.getIs_superuser()=='N'){
                info.setFlag(false);
                info.setMessage("该用户不是管理员用户");
            } else {
                info.setFlag(true);
                info.setObj(user);
            }
        }

        return info;
    }

    @Override
    public List<User> findAllUsers() {
        // 通过Dao查询所有用户
        return adminDao.findAllUsers();
    }

    @Override
    public PageBean<User> findUserByPage(Integer currentPage, Integer rows) {
        PageBean<User> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        // 查询总记录条数
        int totalCount = adminDao.findUserTotalCount();
        pb.setTotalCount(totalCount);
        // 计算开始索引
        int start = (currentPage - 1) * rows;
        // 查询用户
        List<User> users = adminDao.findUserByPage(start, rows);
        pb.setList(users);
        // 计算总页码
        int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows + 1);
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public ResultInfo saveUser(User user) {
        ResultInfo info = new ResultInfo();
        // 根据用户名查询用户
        User findUser = adminDao.findUserByName(user.getUsername());
        if (findUser != null) {
            // 查到用户，用户名存在，保存失败
            info.setFlag(false);
            info.setMessage("用户名已存在");
        } else {
            // 设置激活码
            user.setCode(UuidUtil.getUuid());
            user.setIs_superuser('N');
            // 保存用户信息
            adminDao.addUser(user);
            info.setFlag(true);
        }
        return info;
    }

    @Override
    public void deleteUser(Integer id) {
        adminDao.deleteUser(id);
    }

    @Override
    public User findUserById(Integer id) {
        return adminDao.findUserById(id);
    }

    @Override
    public ResultInfo updateUser(User user) {
        ResultInfo info = new ResultInfo();
        // 根据ID查询用户
        User findIdUser = adminDao.findUserById(user.getId());
        // 根据用户名查询用户
        User findNameUser = adminDao.findUserByName(user.getUsername());
        if (findIdUser == null || findNameUser != null) {
            // 未查到用户或者有重复的用户名，更新失败
            info.setFlag(false);
            info.setMessage("未找到用户或用户名已存在");
        } else {
            // 更新用户信息
            adminDao.updateUser(user);
            info.setFlag(true);
        }
        return info;
    }

    @Override
    public void delSelectUser(String[] ids) {
        for (String id : ids) {
            adminDao.deleteUser(Integer.parseInt(id));
        }
    }

    @Override
    public String findUsernameById(Integer id) {
        User user = adminDao.findUserById(id);
        if (user != null) {
            return user.getUsername();
        } else {
            return null;
        }
    }

    @Override
    public Integer findUidByUname(String uname) {
        User user = adminDao.findUserByName(uname);
        if (user != null) {
            return user.getId();
        } else {
            return null;
        }
    }

    @Override
    public List<Address> findAllAddress(Integer id) {
        return adminDao.findAllAddress(id);
    }

    @Override
    public Address findAddressById(Integer id) {
        return adminDao.findAddressById(id);
    }

    @Override
    public ResultInfo saveAddress(Address addr) {
        ResultInfo info = new ResultInfo();
        User u = adminDao.findUserById(addr.getUser_id());
        if (u == null) {
            info.setFlag(false);
            info.setMessage("用户不存在");
        } else {
            if (addr.getIs_default() == 'Y') {
                // 更改该用户下所有地址为不默认
                adminDao.updateAllAddrDefault(addr.getUser_id());
            }
            adminDao.addAddress(addr);
            info.setFlag(true);
        }

        return info;
    }

    @Override
    public ResultInfo updateAddress(Address addr) {
        ResultInfo info = new ResultInfo();
        // 根据ID查询地址
        Address address = adminDao.findAddressById(addr.getId());
        if (address == null) {
            // 未查到地址，则更新失败
            info.setFlag(false);
            info.setMessage("未找到地址信息");
        } else {
            // 根据地址user_id查询用户
            User u = adminDao.findUserById(addr.getUser_id());
            if (u == null) {
                info.setFlag(false);
                info.setMessage("用户不存在");
            } else {
                if (addr.getIs_default() == 'Y') {
                    // 更改该用户下所有地址为不默认
                    adminDao.updateAllAddrDefault(addr.getUser_id());
                }
                // 更新地址信息
                adminDao.updateAddress(addr);
                info.setFlag(true);
            }
        }
        return info;
    }

    @Override
    public Integer deleteAddress(Integer id) {
        // 根据地址id查询地址
        Address addr = adminDao.findAddressById(id);
        Integer uid = addr.getUser_id();
        // 调用dao层删除地址
        adminDao.deleteAddress(id);

        return uid;
    }

    @Override
    public Integer delSelectAddress(String[] ids) {
        Integer user_id = null;
        for (String id : ids) {
            user_id = this.deleteAddress(Integer.valueOf(id));
        }
        return user_id;
    }

    @Override
    public List<GoodsType> findAllGoodsType() {
        return adminDao.findAllGoodsType();
    }

    @Override
    public GoodsType findGoodsTypeById(Integer id) {
        return adminDao.findGoodsTypeById(id);
    }

    @Override
    public ResultInfo updateGoodsType(GoodsType goodsType) {
        ResultInfo info = new ResultInfo();
        // 根据id查询goodstype
        GoodsType gt = adminDao.findGoodsTypeById(goodsType.getId());
        if (gt == null) {
            info.setFlag(false);
            info.setMessage("更新失败");
        } else {
            adminDao.updateGoodsType(goodsType);
            info.setFlag(true);
        }

        return info;
    }

    @Override
    public PageBean<Goods> findGoodsByPage(Integer currentPage, Integer rows) {
        PageBean<Goods> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        // 查询总记录条数
        int totalCount = adminDao.findGoosdTotalCount();
        pb.setTotalCount(totalCount);
        // 计算开始索引
        int start = (currentPage - 1) * rows;
        // 查询所有商品
        List<Goods> goods = adminDao.findGoodsByPage(start, rows);

        pb.setList(goods);
        // 计算总页码
        int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows + 1);
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public List<GoodsSKU> findAllGoodsSKUByGoodsId(Integer id) {
        return adminDao.findAllGoodsSKUByGoodsId(id);
    }

    @Override
    public List<GoodsSKU> findAllGoodsSKU() {
        return adminDao.findAllGoodsSKU();
    }

    @Override
    public String findGoodsNameById(Integer id) {
        Goods goods = adminDao.findGoodsById(id);
        return goods.getName();
    }

    @Override
    public GoodsSKU findGoodsSKUById(Integer id) {
        return adminDao.findGoodsSkuById(id);
    }

    @Override
    public ResultInfo updateGoodsSKU(GoodsSKU sku) {
        ResultInfo info = new ResultInfo();
        // 根据id查询sku
        GoodsSKU gsk = adminDao.findGoodsSkuById(sku.getId());
        // 根据type_id查询goodstype
        GoodsType gt = adminDao.findGoodsTypeById(sku.getType_id());
        // 根据goods_id查询goods
        Goods goods = adminDao.findGoodsById(sku.getGoods_id());
        if (gsk == null || gt == null || goods == null) {
            info.setFlag(false);
            info.setMessage("更新失败");
        } else {
            adminDao.updateGoodsSKU(sku);
            info.setFlag(true);
        }

        return info;
    }

    @Override
    public List<IndexGoodsBanner> findAllIndexGoodsBanner() {
        return adminDao.findAllIndexGoodsBanner();
    }

    @Override
    public IndexGoodsBanner findIndexGoodsBannerById(Integer id) {
        return adminDao.findIndexGoodsBannerById(id);
    }

    @Override
    public ResultInfo updateIndexGoodsBanner(IndexGoodsBanner indexGoodsBanner) {
        ResultInfo info = new ResultInfo();
        GoodsSKU sku = adminDao.findGoodsSkuById(indexGoodsBanner.getSku_id());
        if(sku == null){
            info.setFlag(false);
            info.setMessage("更新失败");
        } else {
            info.setFlag(true);
            adminDao.updateIndexGoodsBanner(indexGoodsBanner);
        }

        return info;
    }

    @Override
    public void deleteIndexGoodsBanner(Integer id) {
        adminDao.deleteIndexGoodsBanner(id);
    }

    @Override
    public ResultInfo saveIndexBanner(IndexGoodsBanner banner) {
        ResultInfo info = new ResultInfo();
        // 根据id查询Banner
        IndexGoodsBanner igb = adminDao.findIndexGoodsBannerById(banner.getId());
        if(igb!=null){
            info.setFlag(false);
            info.setMessage("Banner已存在");
        } else {
            info.setFlag(true);
            adminDao.addIndexGoodsBanner(banner);
        }

        return info;
    }
}
