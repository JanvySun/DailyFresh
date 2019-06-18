<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>天天生鲜-商品列表</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script type="text/javascript">
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

          if ("${sort}" == "") {
              $("#sort_defalt").prop("href", "javascript:void(0);");
              $("#sort_defalt").prop("class", "active");
              $("#sort_price").prop("href", "javascript:void(0);");
              $("#sort_sales").prop("href", "javascript:void(0);");
              $("#sort_defalt").click(function () {
                  $("#sort_defalt").prop("class", "active");
                  $("#sort_price").prop("class", "");
                  $("#sort_sales").prop("class", "");
                  return false;
              });
              $("#sort_price").click(function () {
                  $("#sort_defalt").prop("class", "");
                  $("#sort_price").prop("class", "active");
                  $("#sort_sales").prop("class", "");
              });
              $("#sort_sales").click(function () {
                  $("#sort_defalt").prop("class", "");
                  $("#sort_price").prop("class", "");
                  $("#sort_sales").prop("class", "active");
              });
          } else {
              if ("${sort}" == "1") {
                  $("#sort_price").prop("class", "active");
              } else if ("${sort}" == "2") {
                  $("#sort_sales").prop("class", "active");
              } else {
                  $("#sort_defalt").prop("class", "active");
              }
          }

          $('.add_goods').click(function () {
              // 获取商品数量
              var $this = $(this);
              var sku_id = $this.attr("sku_id");
              // 发送ajax请求，访问/cart/add, 传递sku_id和count
              var params = {"skuId":sku_id, "count": 1};
              $.post("${pageContext.request.contextPath}/cart/add", params, function (data) {
                  if (data.flag == true) {

                      var $cart = $('#cart');
                      var $count = $('#cart').next();
                      var $btn = $this;
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
      <li><a href="">手机生鲜</a></li>
      <li class="interval">|</li>
      <li><a href="">抽奖</a></li>
    </ul>
  </div>
</div>

<div class="breadcrumb">
  <a href="javascript:void(0);">全部分类</a>
  <span>></span>
  <a href="javascript:void(0);">新鲜水果</a>
</div>

<div class="main_wrap clearfix">
  <div class="l_wrap fl clearfix">
    <div class="new_goods">
      <h3>新品推荐</h3>
      <ul>
        <c:forEach items="${new_sku}" var="sku">
          <li>
            <a href="${pageContext.request.contextPath}/goods/${sku.id}"><img src="${sku.image}"></a>
            <h4><a href="${pageContext.request.contextPath}/goods/${sku.id}">${sku.name}</a></h4>
            <div class="prize">￥${sku.price}</div>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>

  <div class="r_wrap fr clearfix">
    <div class="sort_bar">
      <a href="${pageContext.request.contextPath}/goods/list/${type_id}" id="sort_defalt">默认</a>
      <a href="${pageContext.request.contextPath}/goods/list/${type_id}?sort=1" id="sort_price">价格</a>
      <a href="${pageContext.request.contextPath}/goods/list/${type_id}?sort=2" id="sort_sales">销量</a>
    </div>

    <ul class="goods_type_list clearfix">
      <c:forEach items="${skus}" var="sku">
        <li>
          <a href="${pageContext.request.contextPath}/goods/${sku.id}">
            <img src="${sku.image}">
          </a>
          <h4><a href="${pageContext.request.contextPath}/gooods/${sku.id}">${sku.name}</a></h4>
          <div class="operate">
            <span class="prize">￥${sku.price}</span>
            <span class="unit">销量:${sku.sales}</span>
            <a href="javascript:void(0);" class="add_goods" sku_id="${sku.id}" title="加入购物车"></a>
          </div>
        </li>
      </c:forEach>
    </ul>

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