package cn.hnist.controller;

import cn.hnist.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 */
@Controller
public class IndexView {

    @Autowired
    GoodsService goodsService;

    /**
     * 首页
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        Map<String, List> map = new HashMap<>();
        // 获取商品的种类信息
        //List<GoodsType> types = goodsService.findAllGoodsType();
        map.put("types", goodsService.findAllGoodsType());
        // 获取首页轮播商品信息
        //List<IndexGoodsBanner> goodsBanners = goodsService.findAllIndexBanner();
        map.put("goodsBanners", goodsService.findAllIndexBanner());
        // 获取首页促销活动信息
        //List<IndexPromotionBanner> promotionBanners = goodsService.findAllPromotionBanner();
        map.put("promotionBanners", goodsService.findAllPromotionBanner());
        // 获取首页分类商品展示信息
        //List<IndexTypeBanner> typeBanners = goodsService.findAllTypeGoodsBanner();
        map.put("typeBanners", goodsService.findAllTypeGoodsBanner());
        // 获取用户购物车中商品的数目(模拟)
        mv.addObject("cartCount", 0);

        // 将信息传到页面中
        mv.addAllObjects(map);
        mv.setViewName("index");
        return mv;
    }

}
