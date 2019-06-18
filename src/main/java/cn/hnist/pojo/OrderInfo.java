package cn.hnist.pojo;

/**
 * 订单信息类
 */
public class OrderInfo {

    private String order_id;        // 订单id
    private Integer pay_method;     // 支付方式(1微信 2支付宝 3银行卡)
    private Integer total_count;    // 商品数量
    private Float total_price;      // 商品价格
    private Float transit_price;    // 运费
    private Integer order_status;     // 订单状态(1待支付 2待发货 3待收货 4完成)
    private String trade_no;        // 支付编号
    private Integer addr_id;        // 地址id
    private Integer user_id;        // 用户id

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getPay_method() {
        return pay_method;
    }

    public void setPay_method(Integer pay_method) {
        this.pay_method = pay_method;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public Float getTransit_price() {
        return transit_price;
    }

    public void setTransit_price(Float transit_price) {
        this.transit_price = transit_price;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public Integer getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(Integer addr_id) {
        this.addr_id = addr_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
