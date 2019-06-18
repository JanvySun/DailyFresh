package cn.hnist.pojo;

import java.util.List;

/**
 * 订单vo类，包含订单信息，该订单下订单商品列表，状态名称
 */
public class OrderVo {

    private OrderInfo orderInfo;
    private List<OrderGoods> orderGoods;
    private String status_name;

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(Integer order_status) {
        if (order_status == 1) {
            this.status_name = "待支付";
        } else if (order_status == 2) {
            this.status_name = "待发货";
        } else if (order_status == 3) {
            this.status_name = "待收货";
        } else {
            this.status_name = "完成";
        }
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }
}
