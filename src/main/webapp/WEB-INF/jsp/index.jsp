<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>天天生鲜-首页</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/slide.js"></script>

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
      });
  </script>

</head>
<body>

<div class="header_con">
  <div class="header" id="header"></div>
</div>

<div class="search_bar clearfix" id="search"></div>

<div class="navbar_con">
  <div class="navbar">
    <h1 class="fl">全部商品分类</h1>
    <ul class="navlist fl">
      <li><a href="">首页</a></li>
      <li class="interval">|</li>
      <li><a href="">手机生鲜</a></li>
      <li class="interval">|</li>
      <li><a href="">抽奖</a></li>
    </ul>
  </div>
</div>

<div class="center_con clearfix">
  <ul class="subnav fl">
    <c:forEach items="${typeVos}" var="vo" varStatus="s">
      <li><a href="#model0${s.count}" class="${vo.type.logo}">${vo.type.name}</a></li>
    </c:forEach>
  </ul>

  <div class="slide fl">
    <ul class="slide_pics">
      <c:forEach items="${goodsBanners}" var="banner" varStatus="s">
        <li><a href="${pageContext.request.contextPath}/goods/${banner.sku_id}">
          <img src="${banner.image}" alt="幻灯片"></a></li>
      </c:forEach>
    </ul>
    <div class="prev"></div>
    <div class="next"></div>
    <ul class="points"></ul>
  </div>
  <div class="adv fl">
    <c:forEach items="${promotionBanners}" var="bn" varStatus="s">
      <a href="${bn.url}"><img src="${bn.image}" alt="${bn.name}"></a>
    </c:forEach>
  </div>
</div>

<c:forEach items="${typeVos}" var="typeVo" varStatus="s">
  <div class="list_model">
    <div class="list_title clearfix">
      <h3 class="fl" id="model0${s.count}">${typeVo.type.name}</h3>
      <div class="subtitle fl">
        <span>|</span>
        <c:forEach items="${typeVo.title_banners}" var="banner">
          <a href="${pageContext.request.contextPath}/goods/${banner.sku_id}">${banner.sku.name}</a>
        </c:forEach>
      </div>
      <a href="${pageContext.request.contextPath}/goods/list/${typeVo.type.id}" class="goods_more fr" id="fruit_more">查看更多 ></a>
    </div>

    <div class="goods_con clearfix">
      <div class="goods_banner fl"><img src="${typeVo.type.image}"></div>
      <ul class="goods_list fl">
        <c:forEach items="${typeVo.image_banners}" var="banner">
          <li>
            <h4><a href="${pageContext.request.contextPath}/goods/${banner.sku_id}">${banner.sku.name}</a></h4>
            <a href="${pageContext.request.contextPath}/goods/${banner.sku_id}"><img src="${banner.sku.image}"></a>
            <div class="prize">¥ ${banner.sku.price}</div>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
</c:forEach>

<div class="footer" id="footer"></div>

</body>
</html>