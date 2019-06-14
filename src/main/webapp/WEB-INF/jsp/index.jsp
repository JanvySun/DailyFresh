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
      });

  </script>

</head>
<body>

<div class="header_con">
  <div class="header" id="header"></div>
</div>

<div class="search_bar clearfix">
  <a href="index.jsp" class="logo fl">
    <img src="${pageContext.request.contextPath}/images/logo.png">
  </a>
  <div class="search_con fl">
    <input type="text" class="input_text fl" name="" placeholder="搜索商品">
    <input type="button" class="input_btn fr" name="" value="搜索">
  </div>
  <div class="guest_cart fr">
    <a href="#" class="cart_name fl">我的购物车</a>
    <div class="goods_count fl" id="show_count">1</div>
  </div>
</div>

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
    <c:forEach items="${types}" var="type" varStatus="s">
      <li><a href="#model0${s.count}" class="${type.logo}">${type.name}</a></li>
    </c:forEach>
  </ul>
  <div class="slide fl">
    <ul class="slide_pics">
      <li><img src="${pageContext.request.contextPath}/images/slide.jpg" alt="幻灯片"></li>
      <li><img src="${pageContext.request.contextPath}/images/slide02.jpg" alt="幻灯片"></li>
      <li><img src="${pageContext.request.contextPath}/images/slide03.jpg" alt="幻灯片"></li>
      <li><img src="${pageContext.request.contextPath}/images/slide04.jpg" alt="幻灯片"></li>
    </ul>
    <div class="prev"></div>
    <div class="next"></div>
    <ul class="points"></ul>
  </div>
  <div class="adv fl">
    <c:forEach items="${promotionBanners}" var="bn" varStatus="s">
      <a href="#"><img src="${bn.image}" alt="${bn.name}"></a>
    </c:forEach>
  </div>
</div>

<div class="list_model">
  <div class="list_title clearfix">
    <h3 class="fl" id="model01">新鲜水果</h3>
    <div class="subtitle fl">
      <span>|</span>
      <a href="#">鲜芒</a>
      <a href="#">加州提子</a>
      <a href="#">亚马逊牛油果</a>
    </div>
    <a href="#" class="goods_more fr" id="fruit_more">查看更多 ></a>
  </div>

  <div class="goods_con clearfix">
    <div class="goods_banner fl">
      <img src="${pageContext.request.contextPath}/images/banner01.jpg">
    </div>
    <ul class="goods_list fl">
      <li>
        <h4><a href="#">草莓</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods003.jpg"></a>
        <div class="prize">¥ 30.00</div>
      </li>
      <li>
        <h4><a href="#">葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods002.jpg"></a>
        <div class="prize">¥ 5.50</div>
      </li>
      <li>
        <h4><a href="#">柠檬</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods001.jpg"></a>
        <div class="prize">¥ 3.90</div>
      </li>
      <li>
        <h4><a href="#">奇异果</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods012.jpg"></a>
        <div class="prize">¥ 25.80</div>
      </li>
    </ul>
  </div>
</div>

<div class="list_model">
  <div class="list_title clearfix">
    <h3 class="fl" id="model02">海鲜水产</h3>
    <div class="subtitle fl">
      <span>|</span>
      <a href="#">河虾</a>
      <a href="#">扇贝</a>
    </div>
    <a href="#" class="goods_more fr">查看更多 ></a>
  </div>

  <div class="goods_con clearfix">
    <div class="goods_banner fl">
      <img src="${pageContext.request.contextPath}/images/banner02.jpg">
    </div>
    <ul class="goods_list fl">
      <li>
        <h4><a href="#">青岛野生海捕大青虾</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods018.jpg"></a>
        <div class="prize">¥ 48.00</div>
      </li>
      <li>
        <h4><a href="#">扇贝</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods019.jpg"></a>
        <div class="prize">¥ 46.00</div>
      </li>
      <li>
        <h4><a href="#">冷冻秋刀鱼</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods020.jpg"></a>
        <div class="prize">¥ 19.00</div>
      </li>
      <li>
        <h4><a href="#">基围虾</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods/goods021.jpg"></a>
        <div class="prize">¥ 25.00</div>
      </li>
    </ul>
  </div>
</div>

<div class="list_model">
  <div class="list_title clearfix">
    <h3 class="fl" id="model03">猪牛羊肉</h3>
    <div class="subtitle fl">
      <span>|</span>
      <a href="#">鲜芒</a>
      <a href="#">加州提子</a>
      <a href="#">亚马逊牛油果</a>
    </div>
    <a href="#" class="goods_more fr">查看更多 ></a>
  </div>

  <div class="goods_con clearfix">
    <div class="goods_banner fl">
      <img src="${pageContext.request.contextPath}/images/banner03.jpg">
    </div>
    <ul class="goods_list fl">
      <li>
        <h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
    </ul>
  </div>
</div>

<div class="list_model">
  <div class="list_title clearfix">
    <h3 class="fl" id="model04">禽类蛋品</h3>
    <div class="subtitle fl">
      <span>|</span>
      <a href="#">鲜芒</a>
      <a href="#">加州提子</a>
      <a href="#">亚马逊牛油果</a>
    </div>
    <a href="#" class="goods_more fr">查看更多 ></a>
  </div>

  <div class="goods_con clearfix">
    <div class="goods_banner fl">
      <img src="${pageContext.request.contextPath}/images/banner04.jpg">
    </div>
    <ul class="goods_list fl">
      <li>
        <h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
    </ul>
  </div>
</div>

<div class="list_model">
  <div class="list_title clearfix">
    <h3 class="fl" id="model05">新鲜蔬菜</h3>
    <div class="subtitle fl">
      <span>|</span>
      <a href="#">鲜芒</a>
      <a href="#">加州提子</a>
      <a href="#">亚马逊牛油果</a>
    </div>
    <a href="#" class="goods_more fr">查看更多 ></a>
  </div>

  <div class="goods_con clearfix">
    <div class="goods_banner fl">
      <img src="${pageContext.request.contextPath}/images/banner05.jpg">
    </div>
    <ul class="goods_list fl">
      <li>
        <h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
    </ul>
  </div>
</div>

<div class="list_model">
  <div class="list_title clearfix">
    <h3 class="fl" id="model06">速冻食品</h3>
    <div class="subtitle fl">
      <span>|</span>
      <a href="#">鲜芒</a>
      <a href="#">加州提子</a>
      <a href="#">亚马逊牛油果</a>
    </div>
    <a href="#" class="goods_more fr">查看更多 ></a>
  </div>

  <div class="goods_con clearfix">
    <div class="goods_banner fl">
      <img src="${pageContext.request.contextPath}/images/banner06.jpg">
    </div>
    <ul class="goods_list fl">
      <li>
        <h4><a href="#">维多利亚葡萄维多利亚葡萄维多利亚葡萄维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
      <li>
        <h4><a href="#">维多利亚葡萄</a></h4>
        <a href="#"><img src="${pageContext.request.contextPath}/images/goods.jpg"></a>
        <div class="prize">¥ 38.00</div>
      </li>
    </ul>
  </div>
</div>

<div class="footer" id="footer"></div>

</body>
</html>