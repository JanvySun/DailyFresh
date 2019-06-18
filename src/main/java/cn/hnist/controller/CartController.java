package cn.hnist.controller;

import cn.hnist.pojo.GoodsSKU;
import cn.hnist.pojo.ResultInfo;
import cn.hnist.pojo.User;
import cn.hnist.service.GoodsService;
import cn.hnist.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 购物车控制器
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    /**
     * ajax响应购物车添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo cartAdd(Integer skuId, Integer count, HttpSession session) {
        ResultInfo info = new ResultInfo();
        // 检验商品是否存在
        GoodsSKU sku = goodsService.findGoodsSKUById(skuId);
        if (sku == null) {
            info.setFlag(false);
            info.setMessage("商品不存在");
        }
        // 校验用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            info.setFlag(false);
            info.setMessage("用户未登录，请先登录");
        } else {
            // 校验商品库存
            if (sku.getStock() < count) {
                info.setFlag(false);
                info.setMessage("商品库存不足");
            } else {
                redisService.addCartCount(user.getId(), skuId, count);
                info.setFlag(true);
                info.setMessage("添加成功");
                info.setObj(redisService.getAllCartCount(user.getId()));
            }
        }

        return info;
    }

    /**
     * ajax响应购物车更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo cartUpdate(Integer skuId, Integer count, HttpSession session) {
        ResultInfo info = new ResultInfo();

        // 检验商品是否存在
        GoodsSKU sku = goodsService.findGoodsSKUById(skuId);
        if (sku == null) {
            info.setFlag(false);
            info.setMessage("商品不存在");
        }

        // 校验用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            info.setFlag(false);
            info.setMessage("用户未登录，请先登录");
        } else {
            // 校验商品库存
            if (sku.getStock() < count) {
                info.setFlag(false);
                info.setMessage("商品库存不足");
            } else {
                redisService.updataCart(user.getId(), skuId, count);
                info.setFlag(true);
                info.setMessage("添加成功");
                info.setObj(redisService.getAllCartCount(user.getId()));
            }
        }

        return info;
    }

    /**
     * ajax响应购物车删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo cartDelete(Integer skuId, HttpSession session) {
        ResultInfo info = new ResultInfo();
        // 校验sku是否存在
        GoodsSKU sku = goodsService.findGoodsSKUById(skuId);
        if (sku == null) {
            info.setFlag(false);
            info.setMessage("无效商品");
            return info;
        }
        // 校验用户登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 用户未登录
            info.setFlag(false);
            info.setMessage("您尚未登陆，请登录");
        } else {
            redisService.delCart(user.getId(), skuId);
            info.setFlag(true);
            info.setMessage("删除成功");
            info.setObj(redisService.getAllCartCount(user.getId()));
        }

        return info;
    }
}
