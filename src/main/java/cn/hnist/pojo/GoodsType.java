package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 商品种类模型类
 */
public class GoodsType implements Serializable{

    private Integer id;
    private String name;    // 种类名称
    private String logo;    // 标识，用于控制雪碧图
    private String image;   // 种类的类型图片

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
