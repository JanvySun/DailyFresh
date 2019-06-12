package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 首页分类商品展示模型类
 */
public class IndexTypeBanner implements Serializable{

    private Integer id;
    private Integer display_type;   // 展示类型(0:标题展示；1:图片展示)
    private Integer index;          // 展示顺序
    private Integer sku_id;         // 商品SKU
    private Integer type_id;        // 商品类型

    public Integer getId() {
        return id;
    }

    public Integer getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(Integer display_type) {
        this.display_type = display_type;
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

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "IndexTypeBanner{" +
                "id=" + id +
                ", display_type=" + display_type +
                ", index=" + index +
                ", sku_id=" + sku_id +
                ", type_id=" + type_id +
                '}';
    }
}
