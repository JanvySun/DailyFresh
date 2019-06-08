package cn.hnist.controller;

import cn.hnist.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class View {

    /**
     * 头部
     */
    @RequestMapping("/header")
    public String header() {
        // 判断用户是否登录
        return "header";
    }
    /**
     * 尾部
     */
    @RequestMapping("/footer")
    public String footer() {
        return "footer";
    }

    /**
     * 注册页面
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }


    /**
     * 登录页面
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        // 判断是否记住用户名
        Cookie cookie = CookieUtils.getCookie(request, "username");
        String username = null;
        String remember = null;
        if (null!=cookie){
            username = cookie.getValue();
            remember = "checked";
        }
        mv.addObject("username", username);
        mv.addObject("remember",remember);
        mv.setViewName("login");
        return mv;
    }

    /**
     * 主页
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 用户中心-信息页面
     */
    @RequestMapping("/user/info")
    public String userInfo() {
        return "userCenterInfo";
    }

    /**
     * 用户中心-订单页面
     */
    @RequestMapping("/user/order")
    public String userOrder() {
        return "userCenterOrder";
    }

    /**
     * 用户中心-地址页面
     */
    @RequestMapping("/user/address")
    public String userAddress() {
        return "userCenterSite";
    }

    @RequestMapping("/registOk")
    public String registOk() {
        return "registOk";
    }

}
