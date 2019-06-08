<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>天天生鲜-用户中心</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script>
      $(function () {
          $.get("${pageContext.request.contextPath}/header", function (data) {
              $("#header").html(data);
          });
          $.get("${pageContext.request.contextPath}/footer", function (data) {
              $("#footer").html(data);
          });

      });
  </script>

</head>
<body>
<div class="header_con">
  <div class="header" id="header"></div>
</div>

<div class="search_bar clearfix">
  <a href="index.jsp" class="logo fl"><img src="${pageContext.request.contextPath}/images/logo.png"></a>
  <div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;用户中心</div>
  <div class="search_con fr">
    <input type="text" class="input_text fl" name="" placeholder="搜索商品">
    <input type="button" class="input_btn fr" name="" value="搜索">
  </div>
</div>

<div class="main_con clearfix">
  <div class="left_menu_con clearfix">
    <h3>用户中心</h3>
    <ul>
      <li><a href="${pageContext.request.contextPath}/user/info" class="active">· 个人信息</a></li>
      <li><a href="${pageContext.request.contextPath}/user/order">· 全部订单</a></li>
      <li><a href="${pageContext.request.contextPath}/user/address">· 收货地址</a></li>
    </ul>
  </div>
  <div class="right_content clearfix">
    <div class="info_con clearfix">
      <h3 class="common_title2">基本信息</h3>
      <ul class="user_info_list">
        <li><span>用户名：</span>18210569700</li>
        <li><span>联系方式：</span>18210569700</li>
        <li><span>联系地址：</span>北京市昌平区</li>
      </ul>
    </div>

    <h3 class="common_title2">最近浏览</h3>
    <div class="has_view_list">
      <ul class="goods_type_list clearfix">
        <li>
          <a href="../../detail.html"><img src="${pageContext.request.contextPath}/images/goods/goods003.jpg"></a>
          <h4><a href="../../detail.html">大兴大棚草莓</a></h4>
          <div class="operate">
            <span class="prize">￥16.80</span>
            <span class="unit">16.80/500g</span>
            <a href="#" class="add_goods" title="加入购物车"></a>
          </div>
        </li>

        <li>
          <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods004.jpg"></a>
          <h4><a href="#">吐鲁番梨光杏</a></h4>
          <div class="operate">
            <span class="prize">￥5.50</span>
            <span class="unit">5.50/500g</span>
            <a href="#" class="add_goods" title="加入购物车"></a>
          </div>
        </li>

        <li>
          <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods005.jpg"></a>
          <h4><a href="#">黄肉桃</a></h4>
          <div class="operate">
            <span class="prize">￥10.00</span>
            <span class="unit">10.00/500g</span>
            <a href="#" class="add_goods" title="加入购物车"></a>
          </div>
        </li>

        <li>
          <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods006.jpg"></a>
          <h4><a href="#">进口西梅</a></h4>
          <div class="operate">
            <span class="prize">￥28.80</span>
            <span class="unit">28.8/500g</span>
            <a href="#" class="add_goods" title="加入购物车"></a>
          </div>
        </li>

        <li>
          <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods007.jpg"></a>
          <h4><a href="#">香梨</a></h4>
          <div class="operate">
            <span class="prize">￥6.45</span>
            <span class="unit">6.45/500g</span>
            <a href="#" class="add_goods" title="加入购物车"></a>
          </div>
        </li>
      </ul>
    </div>
  </div>
</div>


<div class="footer" id="footer"> </div>

</body>
</html>