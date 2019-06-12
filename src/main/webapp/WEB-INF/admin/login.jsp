<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>管理员登录</title>

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

  <script type="text/javascript">
      //切换验证码方法
      function refreshCode() {
          // 获取验证码图片对象
          var vcode = $("#vcode");
          // 设置其src属性，加时间戳
          vcode.src = "${pageContext.request.contextPath}/checkCode?time=" + new Date().getTime();
      }
  </script>

</head>
<body>

<div id="box">
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">管理员登录</div>

<div class="container" style="width: 400px;">
  <form action="${pageContext.request.contextPath}/admin/login" method="post">
    <div class="form-group">
      <label for="user">用户名：</label>
      <input type="text" name="username" class="form-control" id="user" placeholder="请输入用户名"/>
    </div>

    <div class="form-group">
      <label for="password">密码：</label>
      <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
    </div>

    <div class="form-inline">
      <label for="vcode">验证码：</label>
      <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码"
             style="width: 120px;"/>
      <a href="javascript:refreshCode();">
        <img src="${pageContext.request.contextPath}/checkCode" title="看不清点击刷新" id="vcode"/>
      </a>
    </div>

    <hr/>

    <div class="form-group" style="text-align: center;">
      <input class="btn btn btn-primary" type="submit" value="登录">
    </div>
  </form>

</div>
</body>
</html>