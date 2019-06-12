package cn.hnist.controller;

import cn.hnist.pojo.Address;
import cn.hnist.pojo.PageBean;
import cn.hnist.pojo.User;
import cn.hnist.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminView {

    @Autowired
    AdminService adminService;

    /**
     * 管理员登录页面
     */
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/admin/login.jsp").forward(request, response);
    }

    /**
     * 用户表页面
     */
    @RequestMapping("/user/list")
    public void userList(Integer page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取参数
        Integer currentPage = (page == null || page < 1) ? 1 : page;
        Integer rows = 5;
        Map<String, String> condition = request.getParameterMap();

        // 查询用户表的所有数据，发给页面
        PageBean<User> pb = adminService.findUserByPage(currentPage, rows);

        request.setAttribute("pb", pb);
        request.getRequestDispatcher("/WEB-INF/admin/userList.jsp").forward(request, response);
    }

    /**
     * 添加用户页面
     */
    @RequestMapping("/user/add")
    public void userAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/admin/userAdd.jsp").forward(request, response);
    }

    /**
     * 修改用户页面
     */
    @RequestMapping("/user/update/{ID}")
    public void userUpdate(@PathVariable("ID") Integer id,
                           HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // 调用业务层查询用户
        User user = adminService.findUserById(id);
        // 将user存入request中
        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/admin/userUpdate.jsp").forward(request, response);
    }

    /**
     * 用户地址页面
     */
    @RequestMapping("/address/list/{ID}")
    public void addressList(@PathVariable("ID") Integer id,
                            HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        // 查询用户地址表的所有数据
        List<Address> addressList = adminService.findAllAddress(id);
        String username = adminService.findUsernameById(id);

        request.setAttribute("addr_list", addressList);
        request.setAttribute("username", username);
        request.getRequestDispatcher("/WEB-INF/admin/addressList.jsp").forward(request, response);
    }

    /**
     * 添加地址页面
     */
    @RequestMapping("/address/add")
    public void addressAdd(String uname, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 查询用户id
        Integer uid = adminService.findUidByUname(uname);
        request.setAttribute("username", uname);
        request.setAttribute("uid",uid);
        request.getRequestDispatcher("/WEB-INF/admin/addressAdd.jsp").forward(request, response);
    }

    /**
     * 修改地址页面
     */
    @RequestMapping("/address/update/{ID}")
    public void addressUpdate(@PathVariable("ID") Integer id,
                           HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        // 调用业务层查询地址
        Address address =  adminService.findAddressById(id);
        // 将地址存入request中
        request.setAttribute("addr", address);

        request.getRequestDispatcher("/WEB-INF/admin/addressUpdate.jsp").forward(request, response);
    }
}
