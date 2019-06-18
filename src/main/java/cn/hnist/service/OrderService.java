package cn.hnist.service;

import cn.hnist.pojo.OrderGoods;
import cn.hnist.pojo.OrderInfo;

import java.util.List;

public interface OrderService {

    /**
     * 添加一条订单
     */
    void addOrderInfo(OrderInfo orderInfo);

    /**
     * 添加一个商品订单
     */
    void addOrderGoods(OrderGoods orderGoods);

    /**
     * 更新order_info中的内容
     */
    void updateOrderInfo(OrderInfo orderInfo);

    /**
     * 根据userid查询所有order_info
     */
    List<OrderInfo> findOrderInfoByUserId(Integer id);

    /**
     * 根据订单id查询OrderGoods
     */
    List<OrderGoods> findOrderGoodsByOrderId(String order_id);

    /**
     * 根据订单id查询OrderInfo
     */
    OrderInfo findOrderInfoById(String order_id);

    /**
     * 更新orderinfo订单状态
     */
    void updateOrderInfoStatus(OrderInfo info);
}
