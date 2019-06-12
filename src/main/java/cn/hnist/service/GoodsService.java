package cn.hnist.service;

import cn.hnist.pojo.GoodsType;
import cn.hnist.pojo.IndexGoodsBanner;
import cn.hnist.pojo.IndexPromotionBanner;
import cn.hnist.pojo.IndexTypeBanner;

import java.util.List;

public interface GoodsService {

    /**
     * 获取所有商品种类
     * @return : 商品种类列表
     */
    List<GoodsType> findAllGoodsType();

    /**
     * 获取所有首页轮播商品信息
     * @return : 首页轮播商品信息列表
     */
    List<IndexGoodsBanner> findAllIndexBanner();

    /**
     * 获取所有首页促销活动信息
     * @return : 首页促销活动信息列表
     */
    List<IndexPromotionBanner> findAllPromotionBanner();

    /**
     * 获取所有首页分类商品展示信息
     * @return : 首页分类商品展示信息
     */
    List<IndexTypeBanner> findAllTypeGoodsBanner();
}
