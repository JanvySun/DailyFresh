package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 商品SPU模型类
 */
public class Goods implements Serializable {

    private Integer id;
    private String name;    // 商品名称
    private String detail;  // 商品详情

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
