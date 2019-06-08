package cn.hnist.controller;

import cn.hnist.pojo.ResultInfo;
import cn.hnist.pojo.User;
import cn.hnist.service.UserService;
import cn.hnist.utils.CookieUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User相关的控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Ajax注册用户
     * @param json : 传入的json字符串
     * @return : 状态，json格式
     */
    @RequestMapping("/registerHandle")
    public @ResponseBody ResultInfo registerHandle(@RequestBody String json) throws IOException {


        // 接收数据，创建User并获取json中的username/password/email
        JsonNode tree = new ObjectMapper().readTree(json);
        User user = new User();
        user.setUsername(tree.path("username").asText());
        user.setPassword(tree.path("password").asText());
        user.setEmail(tree.path("email").asText());
        user.setIs_superuser('N');

        // 进行数据校验

        // 创建响应对象
        ResultInfo info = new ResultInfo();

        // 进行业务处理(用户注册)
        info.setFlag(userService.regist(user));
        if(!info.isFlag()) {
            // 注册失败
            info.setMessage("注册失败");
        }

        // 返回响应对象
        return info;
    }

    /**
     * 通过激活码激活用户
     * @param code : 用户激活码
     * @return : 返回激活状态页面
     */
    @RequestMapping("/activeHandle/{CODE}")
    public ModelAndView activeHandle(@PathVariable("CODE") String code) {
        ResultInfo info = new ResultInfo();
        if (code != null) {
            // 调用Service完成激活
            info.setFlag(userService.active(code));
            if (info.isFlag()) {
                // 激活成功
                info.setMessage("激活成功，请<a href='/login'>登录</a>");
            } else {
                // 激活失败
                info.setMessage("激活失败，您可能已经激活，如未激活请联系管理员");
            }
        }

        // 返回页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("info",info);
        mv.setViewName("active");
        return mv;
    }

    /**
     * ajax请求进行登录操作
     * @param json : 请求的json数据
     * @return : 返回json
     */
    @RequestMapping("/loginHandle")
    public @ResponseBody ResultInfo loginHandle(@RequestBody String json,
                                                HttpServletResponse response,
                                                HttpServletRequest request) throws IOException {

        //System.out.println(json);

        // 解析json数据
        JsonNode tree = new ObjectMapper().readTree(json);
        // 封装User对象
        User user = new User();
        user.setUsername(tree.path("username").asText());
        user.setPassword(tree.path("password").asText());
        // 是否需要记住用户名
        String remember = tree.path("remember").asText();

        // 通过业务层查询User
        User u = userService.findByNameAndPwd(user);

        ResultInfo info = new ResultInfo();
        if (u == null){
            // 用户名或密码错误
            info.setFlag(false);
            info.setMessage("用户名或密码错误");
        } else {
            // 判断用户是否激活
            if (u.getStatus() == 'Y'){
                // 登录成功
                info.setFlag(true);
                // 将用户存入session中
                request.getSession().setAttribute("user",user);
                // 判断是否需要记住用户名
                if("on".equals(remember)){
                    // 记住用户名，添加Cookie
                    CookieUtils.addCookie(response,"username",user.getUsername());
                } else {
                    // 删除Cookie
                    CookieUtils.delCookie(request, response,"username");
                }
            } else {
                // 用户未激活
                info.setFlag(false);
                info.setMessage("您尚未激活，请查看邮件进行激活");
            }
        }

        // 响应数据
        return info;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logoutHandle")
    public String logoutHandle(HttpSession session){
        session.invalidate();
        return "index";
    }
}
