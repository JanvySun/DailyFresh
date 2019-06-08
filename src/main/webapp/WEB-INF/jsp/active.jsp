<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>激活信息</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script>
      $(function () {
          $.get("${pageContext.request.contextPath}/footer", function (data) {
              $("#footer").html(data);
          });
      });
  </script>
  <style>
    #my_box{
      margin:20px auto 0px;
      width:100%;
      height:100px;
    }
    #ok_msg{
      margin: 50px auto 60px;
      text-align:center;
      font-weight:bold;
      height:150px;
      padding-top:100px;
      font-size:30px;
    }
  </style>
</head>
<body>

<div id="my_box">
  <div class="l_con fl">
    <a class="reg_logo"><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
    <div class="reg_slogan">足不出户 · 新鲜每一天</div>
  </div>
</div>
<div id="ok_msg">
  <h4>${info.message}</h4>
</div>

<div class="footer no-mp" id="footer"></div>

</body>
</html>
