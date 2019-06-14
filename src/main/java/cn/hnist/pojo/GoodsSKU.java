package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 商品SKU模型类
 */
public class GoodsSKU implements Serializable{

    private Integer id;
    private String name;        // 商品名称
    private String desc;        // 商品描述
    private Float price;        // 商品价格
    private String unite;       // 商品单位
    private String image;       // 商品图片
    private Integer stock;      // 商品库存
    private Integer sales;      // 商品销量
    private Integer status;     // 商品状态
    private Integer goods_id;   // 商品SPU
    private Integer type_id;    // 商品种类

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "GoodsSKU{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", unite='" + unite + '\'' +
                ", image='" + image + '\'' +
                ", stock=" + stock +
                ", sales=" + sales +
                ", status=" + status +
                ", goods_id=" + goods_id +
                ", type_id=" + type_id +
                '}';
    }
}
