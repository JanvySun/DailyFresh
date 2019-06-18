package cn.hnist.controller;

import cn.hnist.pojo.*;
import cn.hnist.service.GoodsService;
import cn.hnist.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品相关页面
 */
@Controller
public class GoodsView {

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    /**
     * 首页
     */
    @RequestMapping({"/index", "/"})
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Map<String, List> map = new HashMap<>();
        List<IndexTypeVo> typeVos = new ArrayList<>();

        // 获取商品的种类信息
        List<GoodsType> types = goodsService.findAllGoodsType();

        // 获取首页轮播商品信息
        map.put("goodsBanners", goodsService.findAllIndexBanner());
        // 获取首页促销活动信息
        map.put("promotionBanners", goodsService.findAllPromotionBanner());

        // 获取首页分类商品展示信息
        for (GoodsType type : types) {
            IndexTypeVo vo = new IndexTypeVo();
            vo.setType(type);
            vo.setImage_banners(goodsService.findImageIndexTypeByTypeId(type.getId()));
            vo.setTitle_banners(goodsService.findTitleIndexTypeByTypeId(type.getId()));
            typeVos.add(vo);
        }
        map.put("typeVos", typeVos);

        // 获取用户购物车中商品的数目
        Integer cart_count = 0;
        // 如果用户已经登录，则获取用户的购物车数量
        User user = (User) session.getAttribute("user");
        if (user != null) {
            cart_count = redisService.getAllCartCount(user.getId());
        }
        mv.addObject("cartCount", cart_count);

        // 将信息传到页面中
        mv.addAllObjects(map);
        mv.setViewName("index");
        return mv;
    }

    /**
     * 商品详情页面
     */
    @RequestMapping("/goods/{ID}")
    public ModelAndView detailView(@PathVariable("ID") Integer id, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        // 获取种类信息
        mv.addObject("types", goodsService.findAllGoodsType());
        // 根据id查询sku
        GoodsSKU sku = goodsService.findGoodsSKUById(id);
        mv.addObject("sku", sku);
        // 获取所属种类
        mv.addObject("type", goodsService.findGoodsTypeById(sku.getType_id()));
        // 获取对应SPU
        mv.addObject("goods", goodsService.findGoodsById(sku.getGoods_id()));
        // 获取商品评论信息
        List<OrderGoods> skuOrder = goodsService.findOrderGoodsBySkuId(sku.getId());
        mv.addObject("sku_orders", skuOrder);
        // 获取新品信息
        List<GoodsSKU> new_skus = goodsService.findNewGoodsSKUByType(sku.getType_id());
        mv.addObject("new_sku", new_skus);

        // 获取用户购物车中商品的数目
        Integer cart_count = 0;
        // 如果用户已经登录，则获取用户的购物车数量，且添加历史浏览记录
        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 获取购物车数目
            cart_count = redisService.getAllCartCount(user.getId());
            // 添加历史浏览记录
            redisService.addHistory(user.getId(), id);
        }
        mv.addObject("cartCount", cart_count);
        mv.setViewName("detail");
        return mv;
    }

    /**
     * 商品列表页面
     */
    @RequestMapping("/goods/list/{ID}")
    public ModelAndView goodsList(@PathVariable("ID") Integer id, Integer sort) {
        ModelAndView mv = new ModelAndView();
        // 获取种类信息
        mv.addObject("types", goodsService.findAllGoodsType());
        // 获取排序: 1(按价格排序) 2(按销量排序) 其他(默认按id排序)
        int s = sort == null ? 0 : sort;
        mv.addObject("type_id", id);
        mv.addObject("sort", s);
        List<GoodsSKU> skus;
        if (s == 1) {
            skus = goodsService.findGoodsSKUByTypeAndPriceSort(id);
        } else if (s == 2) {
            skus = goodsService.findGoodsSKUByTypeAndSalesSort(id);
        } else {
            skus = goodsService.findGoodsSKUByType(id);
        }
        mv.addObject("skus", skus);

        // 获取新品信息
        List<GoodsSKU> new_skus = goodsService.findNewGoodsSKUByType(id);
        mv.addObject("new_sku", new_skus);

        mv.setViewName("list");
        return mv;
    }


}
