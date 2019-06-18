package cn.hnist.controller;

import cn.hnist.pojo.*;
import cn.hnist.service.AddressService;
import cn.hnist.service.OrderService;
import cn.hnist.service.RedisService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户中心控制器
 */
@Controller
@RequestMapping("/user")
public class UserCenterController {

    @Autowired
    AddressService addressService;

    @Autowired
    RedisService redisService;

    @Autowired
    OrderService orderService;

    /**
     * 用户中心-信息页面
     */
    @RequestMapping("/info")
    public ModelAndView infoView(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> map = new HashMap<>();

        User user = (User) session.getAttribute("user");
        // 根据用户id查询默认地址
        Address addr = addressService.findDefaultAddress(user.getId());
        // 获取用户的个人信息
        map.put("username", user.getUsername());
        if (addr == null) {
            map.put("phone", "无默认");
            map.put("address", "无默认");
        } else {
            map.put("phone", addr.getPhone());
            map.put("address", addr.getAddr());
        }

        // 获取用户的历史浏览记录
        mv.addObject("history", redisService.getUserHistory(user.getId()));

        mv.addAllObjects(map);
        mv.setViewName("userCenterInfo");
        return mv;
    }

    /**
     * 用户中心-订单页面
     */
    @RequestMapping("/order")
    public ModelAndView orderView(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        List<OrderVo> orderVos = new ArrayList<>();
        // 获取用户的订单信息
        User user = (User) session.getAttribute("user");
        List<OrderInfo> orderInfos = orderService.findOrderInfoByUserId(user.getId());
        for (OrderInfo info : orderInfos) {
            OrderVo vo = new OrderVo();
            vo.setOrderInfo(info);
            List<OrderGoods> goods = orderService.findOrderGoodsByOrderId(info.getOrder_id());
            vo.setOrderGoods(goods);
            vo.setStatus_name(info.getOrder_status());
            orderVos.add(vo);
        }

        // 返回页面
        mv.addObject("orderVos", orderVos);
        mv.setViewName("userCenterOrder");
        return mv;
    }

    /**
     * 用户中心-地址页面
     */
    @RequestMapping("/address")
    public ModelAndView addressView(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");

        // 获取用户的收获地址
        Address addr = addressService.findDefaultAddress(user.getId());

        mv.addObject("address", addr);
        mv.setViewName("userCenterSite");
        return mv;
    }

    /**
     * 地址页处理
     */
    @RequestMapping("/addressHandle")
    public @ResponseBody
    ResultInfo addressHandle(@RequestBody String json, HttpSession session) throws IOException {

        ResultInfo info = new ResultInfo();

        // 接收数据
        // 解析json数据，封装到Address类中
        JsonNode tree = new ObjectMapper().readTree(json);
        Address addr = new Address();
        addr.setReceiver(tree.path("receiver").asText());
        addr.setAddr(tree.path("addr").asText());
        addr.setPhone(tree.path("phone").asText());
        addr.setZip_code(tree.path("zip_code").asText());

        // 获取登录的用户的ID
        User user = (User) session.getAttribute("user");
        addr.setUser_id(user.getId());
        System.out.println(addr);

        // 调用业务层添加地址
        boolean flag = addressService.addAddress(addr);
        info.setFlag(flag);
        if (!flag) {
            info.setMessage("添加失败");
        }
        return info;
    }

    /**
     * 用户购物车页面
     */
    @RequestMapping("/cart")
    public ModelAndView cartView(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        // 获取登录用户
        User user = (User) session.getAttribute("user");
        // 获取用户购物车中的信息
        List<CartVo> cartvos = redisService.getAllCartInfo(user.getId());
        Integer count = redisService.getAllCartCount(user.getId());

        mv.addObject("carts", cartvos);
        mv.addObject("count", count);
        mv.setViewName("cart");
        return mv;
    }
}
