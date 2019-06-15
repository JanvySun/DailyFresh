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
              $("#sort_defalt").prop("href", "#");
              $("#sort_defalt").prop("class", "active");
              $("#sort_price").prop("href", "#");
              $("#sort_sales").prop("href", "#");
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
  <a href="#">全部分类</a>
  <span>></span>
  <a href="#">新鲜水果</a>
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
            <span class="unit">${sku.price}/${sku.unite}</span>
            <a href="#" class="add_goods" title="加入购物车"></a>
          </div>
        </li>
      </c:forEach>
    </ul>

  </div>
</div>

<div class="footer" id="footer"></div>

</body>
</html>