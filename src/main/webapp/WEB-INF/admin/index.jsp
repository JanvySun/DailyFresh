<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>管理员首页</title>

  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <style>
    .reg_slogan {
      /*height: 30px;*/
      text-align: center;
      font-size: 30px;
      color: #69a81e;
      /*margin: 10px 0 10px 150px;*/
    }
    #box{
      margin:10px 0 0 20px;
    }
  </style>

</head>
<body>

<div id="box">
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">欢迎您，管理员</div>

<div class="container" style="width: 400px;margin-top: 50px">
  <a class="btn btn-success btn-lg btn-block"
     href="${pageContext.request.contextPath}/admin/user/list">用户表管理</a>
  <a class="btn btn-success btn-lg btn-block"
     href="${pageContext.request.contextPath}/admin/goodsType/list">商品类型管理</a>
  <a class="btn btn-success btn-lg btn-block"
     href="${pageContext.request.contextPath}/admin/goods/list">商品表管理</a>
  <a class="btn btn-success btn-lg btn-block"
     href="${pageContext.request.contextPath}/admin/indexBanner/list">首页轮播图</a>
</div>
</body>
</html>