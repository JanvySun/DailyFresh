package cn.hnist.controller;

import cn.hnist.pojo.ResultInfo;
import cn.hnist.pojo.User;
import cn.hnist.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        user.setUsername(tree.path("username").toString());
        user.setPassword(tree.path("password").toString());
        user.setEmail(tree.path("email").toString());
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

    @RequestMapping("/active/{CODE}")
    public ModelAndView active(@PathVariable("CODE") String code) {
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
}
