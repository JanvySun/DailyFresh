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

          $("#user_name").html("${username}");
          $("#user_phone").html("${phone}");
          $("#user_addr").html("${address}");

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
        <li><span>用户名：</span>
          <p id="user_name"></p></li>
        <li><span>联系方式：</span>
          <p id="user_phone"></p></li>
        <li><span>联系地址：</span>
          <p id="user_addr"></p></li>
      </ul>
    </div>

  </div>
</div>


<div class="footer" id="footer"></div>

</body>
</html>