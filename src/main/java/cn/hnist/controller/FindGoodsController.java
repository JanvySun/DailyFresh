package cn.hnist.controller;

import cn.hnist.pojo.GoodsSKU;
import cn.hnist.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FindGoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView findGoods(String findName) {
        ModelAndView mv = new ModelAndView();
        List<GoodsSKU> skus = goodsService.findGoodsSKUByName(findName);
        mv.addObject("skus", skus);
        // 获取种类信息
        mv.addObject("types", goodsService.findAllGoodsType());
        // 获取新品信息
        List<GoodsSKU> new_skus = goodsService.findNewGoodsSKUByType(1);
        mv.addObject("new_sku", new_skus);

        mv.setViewName("list");
        return mv;
    }

}
