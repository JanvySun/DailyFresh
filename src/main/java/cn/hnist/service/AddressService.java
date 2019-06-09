package cn.hnist.service;

import cn.hnist.pojo.Address;

public interface AddressService {

    /**
     * 添加用户地址信息
     * @param addr : 地址实体类
     * @return : 是否添加成功
     */
    boolean addAddress(Address addr);

    /**
     * 根据用户id获取用户默认收货地址
     * @param id : 用户id
     * @return : 地址实体类
     */
    Address findDefaultAddress(Integer id);
}
