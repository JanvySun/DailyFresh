package cn.hnist.service.impl;

import cn.hnist.dao.GoodsDao;
import cn.hnist.dao.OrderDao;
import cn.hnist.pojo.OrderGoods;
import cn.hnist.pojo.OrderInfo;
import cn.hnist.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServerImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    GoodsDao goodsDao;

    @Override
    public void addOrderInfo(OrderInfo orderInfo) {
        orderDao.addOrderInfo(orderInfo);
    }

    @Override
    public void addOrderGoods(OrderGoods orderGoods) {
        orderDao.addOrderGoods(orderGoods);
    }

    @Override
    public void updateOrderInfo(OrderInfo orderInfo) {
        orderDao.updateOrderInfoPAndC(orderInfo);
    }

    @Override
    public List<OrderInfo> findOrderInfoByUserId(Integer id) {
        return orderDao.findOrderInfoByUserId(id);
    }

    @Override
    public List<OrderGoods> findOrderGoodsByOrderId(String order_id) {
        List<OrderGoods> goods = orderDao.findOrderGoodsByOrderId(order_id);
        for (OrderGoods good : goods) {
            good.setSku(goodsDao.findGoodsSKUById(good.getSku_id()));
        }
        return goods;
    }

    @Override
    public OrderInfo findOrderInfoById(String order_id) {
        return orderDao.findOrderInfoById(order_id);
    }

    @Override
    public void updateOrderInfoStatus(OrderInfo info) {
        orderDao.updateOrderInfoStatus(info);
    }
}
