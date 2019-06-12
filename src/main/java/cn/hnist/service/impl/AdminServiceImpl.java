package cn.hnist.service.impl;

import cn.hnist.dao.AdminDao;
import cn.hnist.pojo.Address;
import cn.hnist.pojo.PageBean;
import cn.hnist.pojo.ResultInfo;
import cn.hnist.pojo.User;
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
        if (findUser!=null) {
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
        if (findIdUser==null || findNameUser!=null) {
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
        if(user!=null){
            return user.getUsername();
        } else {
            return null;
        }
    }

    @Override
    public Integer findUidByUname(String uname) {
        User user = adminDao.findUserByName(uname);
        if(user!=null){
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
        if(u==null){
            info.setFlag(false);
            info.setMessage("用户不存在");
        } else {
            if(addr.getIs_default() == 'Y'){
                // 更改该用户下所有地址为不默认
                adminDao.updateAllAddrDefault(addr.getUser_id());
            }
            adminDao.addAddress(addr);
            info.setFlag(true);
        }

        return info;
    }

}
