<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

          $(".oper_btn").each(function () {
              var status = $(this).attr("status");
              if (status == 1) {
                  $(this).text("去支付");
              } else if (status == 3) {
                  $(this).text("确认收货");
              } else if (status == 4) {
                  $(this).text("已完成");
              }
          });

          $(".oper_btn").click(function () {
              var status = $(this).attr("status");
              var orderId = $(this).attr("order_id");
              var params = {"order_id": orderId};
              if (status == 1) {
                  // 进行支付
                  $.post("${pageContext.request.contextPath}/user/order/pay", params, function (data) {
                      if (data.flag == true) {
                          //引导用户去支付页面
                          window.location.href = data.message;
                      } else {
                          alert(data.message);
                      }
                  });
              } else if (status == 3) {
                  if (confirm("您确定收到货了吗？")) {
                      $.post("${pageContext.request.contextPath}/user/order/success", params, function (data) {
                          if (data.flag == true) {
                              location.reload();
                          } else {
                              alert(data.message);
                          }
                      });
                  }
              }
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
  <div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;用户中心</div>
  <div class="search_con fr">
    <form method="post" action="${pageContext.request.contextPath}/find">
      <input type="text" class="input_text fl" name="findName" placeholder="搜索商品">
      <input type="submit" class="input_btn fr" value="搜索">
    </form>
  </div>
</div>

<div class="main_con clearfix">
  <div class="left_menu_con clearfix">
    <h3>用户中心</h3>
    <ul>
      <li><a href="${pageContext.request.contextPath}/user/info">· 个人信息</a></li>
      <li><a href="${pageContext.request.contextPath}/user/order" class="active">· 全部订单</a></li>
      <li><a href="${pageContext.request.contextPath}/user/address">· 收货地址</a></li>
    </ul>
  </div>
  <div class="right_content clearfix">
    <h3 class="common_title2">全部订单</h3>

    <c:forEach items="${orderVos}" var="vo">
      <ul class="order_list_th w978 clearfix">
        <li class="col02">订单号：${vo.orderInfo.order_id}</li>
        <li class="col02 stress">${vo.status_name}</li>
      </ul>

      <table class="order_list_table w980">
        <tbody>
        <tr>
          <td width="55%">
            <c:forEach items="${vo.orderGoods}" var="orderGoods">
              <ul class="order_goods_list clearfix">
                <li class="col01"><img src="${orderGoods.sku.image}"></li>
                <li class="col02">${orderGoods.sku.name}<em>${orderGoods.sku.price}元/${orderGoods.sku.unite}</em></li>
                <li class="col03">${orderGoods.count}件</li>
                <li class="col04"></li>
              </ul>
            </c:forEach>
          </td>
          <td width="15%">${vo.orderInfo.total_price}元</td>
          <td width="15%">${vo.status_name}</td>
          <td width="15%"><a href="javascript:void(0);" order_id="${vo.orderInfo.order_id}"
                             status="${vo.orderInfo.order_status}" class="oper_btn">去付款</a></td>
        </tr>
        </tbody>
      </table>
    </c:forEach>

  </div>
</div>

<div class="footer" id="footer"></div>

</body>
</html>