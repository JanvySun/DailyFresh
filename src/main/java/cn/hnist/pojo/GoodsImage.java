package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 商品图片模型类
 */
public class GoodsImage implements Serializable {

    private Integer id;
    private String image;   // 图片路径
    private Integer sku_id; // 商品SKU id

    public Integer getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    @Override
    public String toString() {
        return "GoodsImage{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", sku_id=" + sku_id +
                '}';
    }
}
