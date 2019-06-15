package cn.hnist.pojo;

import java.util.List;

public class IndexTypeVo {
    private GoodsType type;
    private List<IndexTypeBanner> image_banners;
    private List<IndexTypeBanner> title_banners;

    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public List<IndexTypeBanner> getImage_banners() {
        return image_banners;
    }

    public void setImage_banners(List<IndexTypeBanner> image_banners) {
        this.image_banners = image_banners;
    }

    public List<IndexTypeBanner> getTitle_banners() {
        return title_banners;
    }

    public void setTitle_banners(List<IndexTypeBanner> title_banners) {
        this.title_banners = title_banners;
    }
}
