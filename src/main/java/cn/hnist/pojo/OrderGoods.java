package cn.hnist.pojo;

/**
 * 商品订单类
 */
public class OrderGoods {

    private Integer id;         // 主键
    private Integer count;      // 商品数目
    private Float price;        // 商品价格
    private String comment;     // 评论
    private String order_id;    // 订单id
    private Integer sku_id;     // sku id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    @Override
    public String toString() {
        return "OrderGoods{" +
                "id=" + id +
                ", count=" + count +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", order_id='" + order_id + '\'' +
                ", sku_id=" + sku_id +
                '}';
    }
}
