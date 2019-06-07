<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>激活信息</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  <style>
    #reg_ok{
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

<div id="reg_ok">
  <div class="l_con fl">
    <a class="reg_logo"><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
    <div class="reg_slogan">足不出户 · 新鲜每一天</div>
  </div>
</div>
<div id="ok_msg">
  <h4>${info.message}</h4>
</div>

<div class="footer no-mp">
  <div class="foot_link">
    <a href="#">关于我们</a>
    <span>|</span>
    <a href="#">联系我们</a>
    <span>|</span>
    <a href="#">招聘人才</a>
    <span>|</span>
    <a href="#">友情链接</a>
  </div>
  <p>CopyRight © 2016 岳阳天天生鲜信息技术有限公司 All Rights Reserved</p>
  <p>电话：0730-8888888 京ICP备8*******8号</p>
</div>

</body>
</html>
