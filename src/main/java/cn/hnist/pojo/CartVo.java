package cn.hnist.pojo;

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
