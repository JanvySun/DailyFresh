<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>天天生鲜-提交订单</title>
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

          <c:forEach items="${addrs}" var="addr">
          if("${addr.is_default}"=="Y"){
              $('input[id="${addr.id}"]').prop("checked", true);
          }
          </c:forEach>

          $('#order_btn').click(function () {
              // 获取用户选择的地址id，支付方式，要购买的商品ids
              var addr_id = $("input[name='addr_id']:checked").val();
              var pay_method = $('input[name="pay_style"]:checked').val();
              var sku_ids = $(this).prop("name");
              // 发送post，访问/user/order/commit
              var params = {"addr_id":addr_id, "pay_method":pay_method, "sku_ids":sku_ids};
              $.post("${pageContext.request.contextPath}/user/order/commit", params, function (data) {
                  if(data.flag==true){
                      // 创建成功
                      localStorage.setItem('order_finish', 2);

                      $('.popup_con').fadeIn('fast', function () {
                          setTimeout(function () {
                              $('.popup_con').fadeOut('fast', function () {
                                  window.location.href = "${pageContext.request.contextPath}/user/order";
                              });
                          }, 2000)
                      });
                  } else {
                      alert(data.message);
                  }
              });


          });
      });
  </script>

</head>
<body>

<div class="header_con">
  <div class="header" id="header"></div>
</div>

<div class="search_bar clearfix">
  <a href="${pageContext.request.contextPath}/" class="logo fl">
    <img src="${pageContext.request.contextPath}/images/logo.png"></a>
  <div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
  <div class="search_con fr">
    <form method="post" action="${pageContext.request.contextPath}/find">
      <input type="text" class="input_text fl" name="findName" placeholder="搜索商品">
      <input type="submit" class="input_btn fr" value="搜索">
    </form>
  </div>
</div>

<h3 class="common_title">确认收货地址</h3>

<div class="common_list_con clearfix">
  <dl>
    <dt>寄送到：</dt>
    <c:forEach items="${addrs}" var="addr">
      <dd>
        <input type="radio" name="addr_id" id="${addr.id}" value="${addr.id}"/>
          ${addr.addr} （${addr.receiver} 收） ${addr.phone}
      </dd>
    </c:forEach>
  </dl>
  <a href="${pageContext.request.contextPath}/user/address" class="edit_site">编辑收货地址</a>

</div>

<h3 class="common_title">支付方式</h3>
<div class="common_list_con clearfix">
  <div class="pay_style_con clearfix">
    <input type="radio" name="pay_style" checked value="2">
    <label class="zhifubao"></label>
  </div>
</div>

<h3 class="common_title">商品列表</h3>

<div class="common_list_con clearfix">
  <ul class="goods_list_th clearfix">
    <li class="col01">商品名称</li>
    <li class="col02">商品单位</li>
    <li class="col03">商品价格</li>
    <li class="col04">数量</li>
    <li class="col05">小计</li>
  </ul>
  <c:forEach items="${skus}" var="vo" varStatus="s">
    <ul class="goods_list_td clearfix">
      <li class="col01">${s.count}</li>
      <li class="col02"><img src="${vo.sku.image}"></li>
      <li class="col03">${vo.sku.name}</li>
      <li class="col04">${vo.sku.unite}</li>
      <li class="col05">${vo.sku.price}元</li>
      <li class="col06">${vo.count}</li>
      <li class="col07">${vo.amount}元</li>
    </ul>
  </c:forEach>
</div>

<h3 class="common_title">总金额结算</h3>

<div class="common_list_con clearfix">
  <div class="settle_con">
    <div class="total_goods_count">共<em>${total_count}</em>件商品，总金额<b>${total_price}元</b></div>
    <div class="transit">运费：<b>${transit_price}元</b></div>
    <div class="total_pay">实付款：<b>${total_pay}元</b></div>
  </div>
</div>

<div class="order_submit clearfix">
  <a href="javascript:void(0);" name="${sku_ids}" id="order_btn">提交订单</a>
</div>

<div class="footer" id="footer"></div>

<div class="popup_con">
  <div class="popup">
    <p>订单提交成功！</p>
  </div>
  <div class="mask"></div>
</div>

</body>
</html>