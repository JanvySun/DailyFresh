<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>天天生鲜-商品详情</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script type="text/javascript">
      // 计算商品总价格
      function cal_goods_amount() {
          // 获取商品单价和数量
          var price = $(".show_pirze").children('em').text();
          var count = $(".num_show").val();
          price = parseFloat(price);
          count = parseInt(count);
          var amount = price * count;
          // 设置商品总价
          $(".total").children("em").text(amount.toFixed(2) + "元");
      }

      $(function () {

          $.get("${pageContext.request.contextPath}/header", function (data) {
              $("#header").html(data);
          });
          $.get("${pageContext.request.contextPath}/footer", function (data) {
              $("#footer").html(data);
          });
          $.get("${pageContext.request.contextPath}/searchBar", function (data) {
              $("#search").html(data);
          });

          cal_goods_amount();

          // 增加商品数量
          $('.add').click(function () {
              // 获取原有数目
              var count = $(".num_show").val();
              count = parseInt(count) + 1;
              $(".num_show").val(count);
              cal_goods_amount();
          });
          // 减少商品数量
          $('.minus').click(function () {
              // 获取原有数目
              var count = $(".num_show").val();
              count = parseInt(count) - 1;
              if (count <= 0) {
                  count = 1;
              }
              $(".num_show").val(count);
              cal_goods_amount();
          });
          // 手动输入商品数量
          $(".num_show").blur(function () {
              // 获取
              var count = $(this).val();
              // 校验
              if (isNaN(count) || count.trim().length == 0 || parseInt(count) <= 0) {
                  count = 1;
              }
              $(this).val(parseInt(count));
              cal_goods_amount();
          });

          $('#add_cart').click(function () {
              // 获取商品数量
              var count = $(".num_show").val();
              // 发送ajax请求，访问/cart/add, 传递sku_id和count
              var params = {"skuId":${sku.id}, "count": count};
              $.post("${pageContext.request.contextPath}/cart/add", params, function (data) {
                  if (data.flag == true) {

                      var $cart = $('#cart');
                      var $count = $('#cart').next();
                      var $btn = $('#add_cart');
                      var $point = $('#add_jump');

                      var $w01 = $btn.outerWidth();
                      var $h01 = $btn.outerHeight();

                      var $w02 = $cart.outerWidth();
                      var $h02 = $cart.outerHeight();

                      var oPos01 = $btn.offset();
                      var oPos02 = $cart.offset();

                      $point.css({
                          'left': oPos01.left + parseInt($w01 / 2) - 8,
                          'top': oPos01.top + parseInt($h01 / 2) - 8
                      });
                      $point.show();
                      $point.stop().animate({
                              'left': oPos02.left + parseInt($w02 / 2) - 8,
                              'top': oPos02.top + parseInt($h01 / 2) - 8
                          },
                          500, function () {
                              $point.hide();
                              $count.html(data.obj);
                          });
                  } else {
                      $("#pop_msg").text(data.message);
                      $('.popup_con').fadeIn('fast');
                      $(".num_show").val(1);
                      cal_goods_amount();
                  }
              });
          });

          $(document).click(function () {
              $('.popup_con').fadeOut();
          });
      });
  </script>

</head>
<body>

<div class="header_con">
  <div class="header" id="header"></div>
</div>

<div class="search_bar clearfix" id="search"></div>

<div class="navbar_con">
  <div class="navbar clearfix">
    <div class="subnav_con fl">
      <h1>全部商品分类</h1>
      <span></span>
      <ul class="subnav">
        <c:forEach items="${types}" var="type">
          <li><a href="${pageContext.request.contextPath}/goods/list/${type.id}"
                 class="${type.logo}">${type.name}</a></li>
        </c:forEach>
      </ul>
    </div>
    <ul class="navlist fl">
      <li><a href="${pageContext.request.contextPath}/">首页</a></li>
      <li class="interval">|</li>
      <li><a href="javascript:void(0);">手机生鲜</a></li>
      <li class="interval">|</li>
      <li><a href="javascript:void(0);">抽奖</a></li>
    </ul>
  </div>
</div>

<div class="breadcrumb">
  <a href="javascript:void(0);">全部分类</a>
  <span>></span>
  <a href="javascript:void(0);">${type.name}</a>
  <span>></span>
  <a href="javascript:void(0);">商品详情</a>
</div>

<div class="goods_detail_con clearfix">
  <div class="goods_detail_pic fl"><img src="${sku.image}" width="320" height="320"></div>

  <div class="goods_detail_list fr">
    <h3>${sku.name}</h3>
    <p>${sku.desc}</p>
    <div class="prize_bar">
      <span class="show_pirze">¥<em>${sku.price}</em></span>
      <span class="show_unit">单  位：${sku.unite}</span>
      <span class="show_unit">销  量：${sku.sales}</span>
    </div>
    <div class="goods_num clearfix">
      <div class="num_name fl">数 量：</div>
      <div class="num_add fl">
        <input type="text" class="num_show fl" value="1">
        <a href="javascript:void(0);" class="add fr">+</a>
        <a href="javascript:void(0);" class="minus fr">-</a>
      </div>
      <div class="num_name fl" style="margin-left: 120px">库 存：${sku.stock}</div>
    </div>
    <div class="total">总价：<em>16.80元</em></div>
    <div class="operate_btn">
      <a href="javascript:void(0);" class="buy_btn">立即购买</a>
      <a href="javascript:void(0);" class="add_cart" id="add_cart">加入购物车</a>
    </div>
  </div>
</div>

<div class="main_wrap clearfix">
  <div class="l_wrap fl clearfix">
    <div class="new_goods">
      <h3>新品推荐</h3>
      <ul>
        <c:forEach items="${new_sku}" var="news">
          <li>
            <a href="${pageContext.request.contextPath}/goods/${news.id}"><img src="${news.image}"></a>
            <h4><a href="${pageContext.request.contextPath}/goods/${news.id}">${news.name}</a></h4>
            <div class="prize">￥${news.price}</div>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>

  <div class="r_wrap fr clearfix">
    <ul class="detail_tab clearfix">
      <li class="active">商品介绍</li>
      <%--<li>评论</li>--%>
    </ul>

    <div class="tab_content">
      <dl>
        <dt>商品详情：</dt>
        <dd>${goods.detail}</dd>
      </dl>
    </div>

  </div>
</div>

<div class="footer" id="footer"></div>

<div class="popup_con">
  <div class="popup">
    <p id="pop_msg"></p>
  </div>
  <div class="mask"></div>
</div>

<div class="add_jump" id="add_jump"></div>

</body>
</html>