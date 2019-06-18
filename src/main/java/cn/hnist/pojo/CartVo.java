package cn.hnist.pojo;

/**
 * 购物车vo类，包含商品sku，总数量，总金额
 */
public class CartVo {

    private GoodsSKU sku;
    private Integer count;
    private Float amount;

    public GoodsSKU getSku() {
        return sku;
    }

    public void setSku(GoodsSKU sku) {
        this.sku = sku;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
