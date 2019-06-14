package cn.hnist.controller;

import cn.hnist.pojo.*;
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
     * 管理员首页
     */
    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request, response);
    }

    // ----------- 用户表相关页面 -----------
    //region 用户表相关页面

    /**
     * 用户表页面
     */
    @RequestMapping("/user/list")
    public void userList(Integer page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取参数
        Integer currentPage = (page == null || page < 1) ? 1 : page;
        Integer rows = 5;

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
    //endregion
    // ====================================


    // ----------- 地址表相关页面 -----------
    //region 地址表相关页面

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
        request.setAttribute("uid", uid);
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
        Address address = adminService.findAddressById(id);
        // 将地址存入request中
        request.setAttribute("addr", address);

        request.getRequestDispatcher("/WEB-INF/admin/addressUpdate.jsp").forward(request, response);
    }
    //endregion
    // ====================================


    // ----------- 商品类型表相关页面 -----------
    //region 商品类型表相关页面

    /**
     * 商品类型表页面
     */
    @RequestMapping("/goodsType/list")
    public void goodsTypeList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 查询goods_type的所有数据，发给页面
        List<GoodsType> goodsTypes = adminService.findAllGoodsType();
        request.setAttribute("goods_types", goodsTypes);

        request.getRequestDispatcher("/WEB-INF/admin/goodsTypeList.jsp").forward(request, response);
    }

    /**
     * 商品类型修改页面
     */
    @RequestMapping("/goodsType/update/{ID}")
    public void goodsTypeUpdate(@PathVariable("ID") Integer id,
                                HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        // 调用业务层查询商品类型
        GoodsType goodsType = adminService.findGoodsTypeById(id);
        // 存入request中
        request.setAttribute("type", goodsType);

        request.getRequestDispatcher("/WEB-INF/admin/goodsTypeUpdate.jsp").forward(request, response);
    }
    //endregion
    // ========================================


    // ----------- 商品表相关页面 -----------

    /**
     * 商品SPU表页面
     */
    @RequestMapping("/goods/list")
    public void goodsList(Integer page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 获取参数
        Integer currentPage = (page == null || page < 1) ? 1 : page;
        Integer rows = 5;
        Map<String, String> condition = request.getParameterMap();

        // 查询goods表的所有数据，发给页面
        PageBean<Goods> goods = adminService.findGoodsByPage(currentPage, rows);
        request.setAttribute("pb", goods);

        request.getRequestDispatcher("/WEB-INF/admin/goodsList.jsp").forward(request, response);
    }

    /**
     * 商品SKU表页面
     */
    @RequestMapping("/goods_sku/list/{ID}")
    public void goodsSKUList(@PathVariable("ID") Integer id,
                             HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        // 查询某商品其SKU表的所有数据
        List<GoodsSKU> goodsSKUList = adminService.findAllGoodsSKUByGoodsId(id);
        String goodsName = adminService.findGoodsNameById(id);

        request.setAttribute("sku_list", goodsSKUList);
        request.setAttribute("goodsName", goodsName);
        request.getRequestDispatcher("/WEB-INF/admin/goodsSKUList.jsp").forward(request, response);
    }

    /**
     * 商品SKU修改页面
     */
    @RequestMapping("/goods_sku/update/{ID}")
    public void goodsSKUUpdate(@PathVariable("ID") Integer id,
                               HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        // 调用业务层查询商品类型
        GoodsSKU goodsSKU = adminService.findGoodsSKUById(id);
        // 存入request中
        request.setAttribute("sku", goodsSKU);

        request.getRequestDispatcher("/WEB-INF/admin/goodsSKUUpdate.jsp").forward(request, response);
    }

    /**
     * 首页轮播图列表
     */
    @RequestMapping("/indexBanner/list")
    public void indexBannerList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 查询index_banner表所有信息
        List<IndexGoodsBanner> banners = adminService.findAllIndexGoodsBanner();
        // 存入request中
        request.setAttribute("banner_list", banners);

        request.getRequestDispatcher("/WEB-INF/admin/indexBannerList.jsp").forward(request, response);
    }

    /**
     * 首页轮播图修改页面
     */
    @RequestMapping("/indexBanner/update/{ID}")
    public void indexBannerUpdate(@PathVariable("ID") Integer id,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        // 调用业务层查询商品类型
        IndexGoodsBanner indexGoodsBanner = adminService.findIndexGoodsBannerById(id);
        // 存入request中
        request.setAttribute("banner", indexGoodsBanner);

        request.getRequestDispatcher("/WEB-INF/admin/indexBannerUpdate.jsp").forward(request, response);
    }

    /**
     * 首页轮播图添加页面
     */
    @RequestMapping("/indexBanner/add")
    public void indexBannerAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 查询所有sku
        List<GoodsSKU> sku_list = adminService.findAllGoodsSKU();
        // 存入request中
        request.setAttribute("sku_list", sku_list);

        request.getRequestDispatcher("/WEB-INF/admin/indexBannerAdd.jsp").forward(request, response);
    }
    // =======================================


    /**
     * 图片上传页面
     */
    @RequestMapping("/pictureUpload")
    public void picUpload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/admin/picUpload.jsp").forward(request, response);
    }
}
