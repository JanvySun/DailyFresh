package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 首页促销活动模型类
 */
public class IndexPromotionBanner implements Serializable {

    private Integer id;
    private String name;    // 活动名称
    private String url;     // 活动链接
    private String image;   // 活动图片
    private Integer od;     // 展示顺序

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOd() {
        return od;
    }

    public void setOd(Integer od) {
        this.od = od;
    }

}
