package cn.hnist.controller;

import cn.hnist.pojo.Address;
import cn.hnist.pojo.ResultInfo;
import cn.hnist.pojo.User;
import cn.hnist.service.AddressService;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 用户中心控制器
 */
@Controller
@RequestMapping("/user")
public class UserCenterController {

    @Autowired
    AddressService addressService;

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
        map.put("phone", addr.getPhone());
        map.put("address", addr.getAddr());

        // 获取用户的历史浏览记录

        mv.addAllObjects(map);
        mv.setViewName("userCenterInfo");
        return mv;
    }

    /**
     * 用户中心-订单页面
     */
    @RequestMapping("/order")
    public String orderView() {
        // 获取用户的订单信息
        return "userCenterOrder";
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

        mv.addObject("address",addr);
        mv.setViewName("userCenterSite");
        return mv;
    }

    /**
     * 地址页处理
     */
    @RequestMapping("/addressHandle")
    public @ResponseBody ResultInfo addressHandle(@RequestBody String json, HttpSession session) throws IOException {

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
}
