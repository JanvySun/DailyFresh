package cn.hnist.service.impl;

import cn.hnist.dao.AddressDao;
import cn.hnist.dao.UserDao;
import cn.hnist.pojo.Address;
import cn.hnist.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    @Autowired
    UserDao userDao;

    @Override
    public boolean addAddress(Address addr) {
        // 根据用户id查询用户
        if (userDao.findById(addr.getUser_id()) == null) {
            // 用户不存在返回false
            return false;
        }

        // 根据用户id查询其所有地址
        List<Address> addresses = addressDao.findUserAddress(addr.getUser_id());
        if (addresses == null) {
            // 用户没有默认地址
            addr.setIs_default('Y');
        } else {
            // 用户有默认地址
            addr.setIs_default('N');
        }
        addressDao.saveAddress(addr);
        return true;
    }

    @Override
    public Address findDefaultAddress(Integer id) {
        // 根据用户id查询默认地址
        List<Address> addresses = addressDao.findUserAddress(id);
        for (Address address : addresses) {
            if(address.getIs_default() == 'Y'){
                return address;
            }
        }
        return null;
    }
}
