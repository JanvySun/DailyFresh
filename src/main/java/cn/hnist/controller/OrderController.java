package cn.hnist.controller;

import cn.hnist.pojo.*;
import cn.hnist.service.GoodsService;
import cn.hnist.service.OrderService;
import cn.hnist.service.RedisService;
import cn.hnist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单控制器
 */
@Controller
@RequestMapping("/user/order")
public class OrderController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;


    /**
     * 生成订单页面
     */
    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public ModelAndView orderPlace(Integer[] sku_ids, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        // 获取登录用户
        User user = (User) session.getAttribute("user");
        List<CartVo> skus = new ArrayList<>();
        // 商品总件数和总价格
        Integer totalCount = 0;
        Float totalPrice = 0F;
        for (Integer skuId : sku_ids) {
            CartVo vo = new CartVo();
            // 获取sku
            GoodsSKU sku = goodsService.findGoodsSKUById(skuId);
            vo.setSku(sku);
            // 获取用户购物车某物品数量
            int count = redisService.getOneCount(user.getId(), sku.getId());
            float amount = sku.getPrice() * count;

            vo.setCount(count);
            vo.setAmount(amount);

            totalCount += count;
            totalPrice += amount;

            skus.add(vo);
        }
        // 运费
        Float transitPrice = 10.0f;
        // 实需付款
        Float totalPay = totalPrice + transitPrice;
        // 获取用户所有地址
        List<Address> addresses = userService.findAllAddress(user.getId());

        //region Arrays.toString源码改进
        int iMax = sku_ids.length - 1;
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(String.valueOf(sku_ids[i]));
            if (i == iMax)
                break;
            b.append(", ");
        }
        //endregion

        mv.addObject("skus", skus);
        mv.addObject("total_count", totalCount);
        mv.addObject("total_price", totalPrice);
        mv.addObject("transit_price", transitPrice);
        mv.addObject("total_pay", totalPay);
        mv.addObject("addrs", addresses);
        mv.addObject("sku_ids", b);

        mv.setViewName("placeOrder");
        return mv;
    }

    /**
     * ajax响应订单提交
     */
    @RequestMapping("/commit")
    @ResponseBody
    public ResultInfo orderCommit(Integer addr_id,
                                  Integer pay_method,
                                  Integer[] sku_ids,
                                  HttpSession session) {

        ResultInfo info = new ResultInfo();
        // 获取登录用户
        User user = (User) session.getAttribute("user");
        // 创建订单类
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAddr_id(addr_id);
        orderInfo.setUser_id(user.getId());
        orderInfo.setPay_method(pay_method);
        orderInfo.setOrder_id(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        Integer total_count = 0;
        Float total_price = 0.0F;
        // 业务层添加订单
        orderService.addOrderInfo(orderInfo);
        for (Integer skuID : sku_ids) {
            GoodsSKU sku = goodsService.findGoodsSKUById(skuID);
            Integer count = redisService.getOneCount(user.getId(), skuID);
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setCount(count);
            orderGoods.setOrder_id(orderInfo.getOrder_id());
            orderGoods.setPrice(sku.getPrice());
            orderGoods.setSku_id(sku.getId());
            // 业务层添加orderGoods
            orderService.addOrderGoods(orderGoods);
            // 更新商品的库存和销量
            sku.setStock(sku.getStock() - count);
            sku.setSales(sku.getSales() + count);
            goodsService.updateSku(sku);
            // 计算总数量和总金额
            total_count += count;
            total_price += sku.getPrice() * count;

            // 清除用户购物车中的记录
            redisService.delCart(user.getId(), skuID);
        }

        // 更新订单info表中的总数量和总金额
        orderInfo.setTotal_count(total_count);
        orderInfo.setTotal_price(total_price);
        orderService.updateOrderInfo(orderInfo);

        // 返回应答
        info.setFlag(true);
        return info;
    }

    /**
     * ajax响应订单支付
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo pay(String order_id, HttpSession session) throws UnsupportedEncodingException {
        ResultInfo info = new ResultInfo();
        // 获取登录的user
        User user = (User) session.getAttribute("user");
        OrderInfo orderInfo = orderService.findOrderInfoById(order_id);
        if (orderInfo == null || !orderInfo.getUser_id().equals(user.getId()) || orderInfo.getOrder_status() != 1) {
            info.setFlag(false);
            info.setMessage("无效的订单id");
        } else {
            info.setFlag(true);
            orderInfo.setOrder_status(3);
            orderService.updateOrderInfoStatus(orderInfo);
            info.setMessage("http://localhost/user/alipay/" + order_id);
        }

        return info;
    }

    /**
     * ajax响应订单收获成功
     */
    @RequestMapping(value = "/success", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo success(String order_id, HttpSession session) throws UnsupportedEncodingException {
        ResultInfo info = new ResultInfo();
        // 获取登录的user
        User user = (User) session.getAttribute("user");
        OrderInfo orderInfo = orderService.findOrderInfoById(order_id);
        if (orderInfo == null || !orderInfo.getUser_id().equals(user.getId())) {
            info.setFlag(false);
            info.setMessage("无效的订单id");
        } else {
            info.setFlag(true);
            orderInfo.setOrder_status(4);
            orderService.updateOrderInfoStatus(orderInfo);
        }

        return info;
    }


}
