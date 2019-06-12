package cn.hnist.controller;

import cn.hnist.pojo.Address;
import cn.hnist.pojo.ResultInfo;
import cn.hnist.pojo.User;
import cn.hnist.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员控制器
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * Ajax响应管理员登录请求
     */
    @RequestMapping(value = "/loginHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo loginHandle(@RequestBody String json) {
        // 获取数据
        // 验证码校验
        // 封装对象
        // Service查询
        // 判断是否登录成功
        return null;
    }

    /**
     * Ajax响应添加用户
     */
    @RequestMapping(value = "/user/addHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addUserHandle(String name, String pwd, String email, Character status){
        // 获取数据，封装User
        User user = new User();
        user.setUsername(name);
        user.setPassword(pwd);
        user.setEmail(email);
        user.setStatus(status);

        // 调用业务层进行添加
        return adminService.saveUser(user);
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/user/del/{ID}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("ID") Integer id){
        // 调用业务层删除用户
        adminService.deleteUser(id);
        return "redirect:/admin/user/list";
    }

    /**
     * 删除所有选中
     */
    @RequestMapping(value = "/user/delSelect", method = RequestMethod.POST)
    public String delSelectUser(HttpServletRequest request) {
        String[] ids = request.getParameterValues("uid");
        // 调用业务层删除选中
        adminService.delSelectUser(ids);
        return "redirect:/admin/user/list";
    }

    /**
     * Ajax响应更新用户
     */
    @RequestMapping(value = "/user/updateHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateUserHandle(Integer id,
                                       String name,
                                       String pwd,
                                       String email,
                                       Character status){

        // 获取数据，封装User
        User user = new User();
        user.setId(id);
        user.setUsername(name);
        user.setPassword(pwd);
        user.setEmail(email);
        user.setStatus(status);

        // 调用业务更新
        return adminService.updateUser(user);
    }

    /**
     * Ajax响应添加地址
     */
    @RequestMapping(value = "/address/addHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addAddressHandle(String name,
                                       String addr,
                                       String zipCode,
                                       String phone,
                                       Character status,
                                       Integer uid){
        // 封装Address对象
        Address a = new Address();
        a.setReceiver(name);
        a.setAddr(addr);
        a.setZip_code(zipCode);
        a.setPhone(phone);
        a.setIs_default(status);
        a.setUser_id(uid);

        return adminService.saveAddress(a);
    }

    /**
     * Ajax响应更新地址
     */
    @RequestMapping(value = "/address/updateHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateAddressHandle(Address address){
        System.out.println(address);
        return null;
    }
}
