package cn.hnist.controller;

import cn.hnist.pojo.*;
import cn.hnist.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public ResultInfo loginHandle(String username, String password, String verifycode,
                                  HttpSession session) {
        ResultInfo info = new ResultInfo();

        // 验证码校验
        String code = (String) session.getAttribute("CHECKCODE_SERVER");
        if (!verifycode.equalsIgnoreCase(code)) {
            // 验证码不正确
            info.setFlag(false);
            info.setMessage("验证码错误");
        } else {
            // 销毁验证码，保证其一次性使用
            session.removeAttribute("CHECKCODE_SERVER");
            // 调用业务进行登录
            info = adminService.login(username, password);
        }

        if (info.isFlag()) {
            // 登录成功，将用户存入session中
            session.setAttribute("admin", info.getObj());
        }

        return info;
    }

    // ----------- 用户表相关响应 -----------
    //region 用户表相关响应

    /**
     * Ajax响应添加用户
     */
    @RequestMapping(value = "/user/addHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addUserHandle(String name, String pwd, String email, Character status) {
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
    @RequestMapping(value = "/user/del/{ID}")
    public String deleteUser(@PathVariable("ID") Integer id) {
        // 调用业务层删除用户
        adminService.deleteUser(id);
        return "redirect:/admin/user/list";
    }

    /**
     * 删除所有选中用户
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
                                       Character status) {

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
    //endregion
    // ===============================


    // ----------- 地址表相关响应 -----------
    //region 地址表相关响应

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
                                       Integer uid) {
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
    public ResultInfo updateAddressHandle(Address address) {
        // 调用业务更新地址
        return adminService.updateAddress(address);
    }

    /**
     * 删除地址
     */
    @RequestMapping(value = "/address/del/{ID}")
    public String deleteAddress(@PathVariable("ID") Integer id) {
        // 调用业务层删除地址
        Integer user_id = adminService.deleteAddress(id);
        return String.format("redirect:/admin/address/list/%d", user_id);
    }

    /**
     * 删除所有选中地址
     */
    @RequestMapping(value = "/address/delSelect", method = RequestMethod.POST)
    public String delSelectAddress(HttpServletRequest request) {
        String[] ids = request.getParameterValues("uid");
        // 调用业务层删除选中地址
        Integer user_id = adminService.delSelectAddress(ids);
        return String.format("redirect:/admin/address/list/%d", user_id);
    }
    //endregion
    // ===============================


    // ----------- 商品表相关响应 -----------
    //region 商品种类表相关响应

    /**
     * Ajax响应更新商品种类
     */
    @RequestMapping(value = "/goodsType/updateHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateGoodsTypeHandle(Integer id, String image_url) {

        // 获取数据，封装GoodsType
        GoodsType goodsType = new GoodsType();
        goodsType.setId(id);
        goodsType.setImage(image_url);

        // 调用业务更新
        return adminService.updateGoodsType(goodsType);
    }

    /**
     * Ajax响应更新商品sku
     */
    @RequestMapping(value = "/goods_sku/updateHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateGoodsSKUHandle(GoodsSKU sku) {

        // 调用业务更新
        return adminService.updateGoodsSKU(sku);
    }

    /**
     * Ajax响应index_banner更新
     */
    @RequestMapping(value = "/indexBanner/updateHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo updateIndexBanner(IndexGoodsBanner indexGoodsBanner) {

        // 调用业务更新
        return adminService.updateIndexGoodsBanner(indexGoodsBanner);
    }

    /**
     * index_banner删除
     */
    @RequestMapping("/indexBanner/del/{ID}")
    public String deleteIndexBanner(@PathVariable("ID") Integer id) {
        // 调用业务层删除indexBanner
        adminService.deleteIndexGoodsBanner(id);
        return "redirect:/admin/indexBanner/list";
    }

    /**
     * Ajax 响应index_banner添加
     */
    @RequestMapping(value = "/indexBanner/addHandle", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addIndexBanner(IndexGoodsBanner banner) {
        return adminService.saveIndexBanner(banner);
    }
    //endregion
    // ========================================


}
