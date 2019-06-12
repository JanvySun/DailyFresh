package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 首页轮播商品展示模型类
 */
public class IndexGoodsBanner implements Serializable{

    private Integer id;
    private String image;   // 图片
    private Integer index;  // 展示顺序，如0 1 2 3
    private Integer sku_id; // 商品sku

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "IndexGoodsBanner{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", index=" + index +
                ", sku_id=" + sku_id +
                '}';
    }
}
