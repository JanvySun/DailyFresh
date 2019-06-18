package cn.hnist.controller;

import cn.hnist.pojo.User;
import cn.hnist.service.RedisService;
import cn.hnist.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 页面控制器
 */
@Controller
public class View {

    @Autowired
    RedisService redisService;

    /**
     * 头部
     */
    @RequestMapping("/header")
    public String header() {
        return "header";
    }

    /**
     * 查询部分
     */
    @RequestMapping("/searchBar")
    public ModelAndView searchBar(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Integer cart_count = 0;
        if (user != null) {
            // 如果用户登录
            cart_count = redisService.getAllCartCount(user.getId());
        }
        mv.addObject("cart_count", cart_count);

        mv.setViewName("searchBar");
        return mv;
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
    public ModelAndView register() {
        return new ModelAndView("register");
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
        if (null != cookie) {
            username = cookie.getValue();
            remember = "checked";
        }
        mv.addObject("username", username);
        mv.addObject("remember", remember);
        mv.addObject("refer", next);
        mv.setViewName("login");
        return mv;
    }

    /**
     * 注册成功页面
     */
    @RequestMapping("/registOk")
    public ModelAndView registOk() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "注册成功");
        mv.addObject("info", "恭喜，注册成功！请登录您的注册邮箱进行激活您的账号，激活后才能登录。");
        mv.setViewName("info");
        return mv;
    }

    /**
     * 支付成功页面
     */
    @RequestMapping("/payOk")
    public ModelAndView payOk() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "支付成功");
        mv.addObject("info", "交易支付成功，正在跳转至商户页面");
        mv.setViewName("info");
        return mv;
    }
}
