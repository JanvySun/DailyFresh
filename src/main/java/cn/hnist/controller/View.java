package cn.hnist.controller;

import cn.hnist.utils.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView login(@RequestParam(required = false) String next, HttpServletRequest request) {
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
        mv.addObject("refer", next);
        mv.setViewName("login");
        return mv;
    }

    /**
     * index页面
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 注册成功页面
     */
    @RequestMapping("/registOk")
    public String registOk(Model model) {
        model.addAttribute("title", "注册成功");
        model.addAttribute("info","恭喜，注册成功！请登录您的注册邮箱进行激活您的账号，激活后才能登录。");
        return "info";
    }
}
