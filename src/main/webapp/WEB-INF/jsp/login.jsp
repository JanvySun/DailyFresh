<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>天天生鲜-登录</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script>

      $(function () {
          $.get("${pageContext.request.contextPath}/footer", function (data) {
              $("#footer").html(data);
          });
          // 表单提交事件
          $("#login_form").submit(function () {
              // 获取用户名和密码
              var username = $("#uname").val();
              var password = $("#pwd").val();
              var remember = $("#remember").val();
              // 发送ajax请求，提交表单数据
              $.ajax({
                  url:"${pageContext.request.contextPath}/user/loginHandle",
                  type:"post",
                  data:JSON.stringify({username:username,password:password,remember:remember}),
                  contentType: "application/json;charset=utf-8",
                  dataType:"json",
                  success : function (data) {
                      if(data.flag) {
                          // 登录成功，跳转到下一页
                          var next = "${refer}" == "" ? "/index" : "${refer}";
                          location.href = "${pageContext.request.contextPath}" + next;
                      } else {
                          // 登录失败
                          alert(data.message);
                      }
                  }
              });
              // 不让表单提交，我们自己进行处理
              return false;
          });

      });

  </script>

</head>
<body>
<div class="login_top clearfix">
  <a href="${pageContext.request.contextPath}/index" class="login_logo">
    <img src="${pageContext.request.contextPath}/images/logo02.png">
  </a>
</div>

<div class="login_form_bg">
  <div class="login_form_wrap clearfix">
    <div class="login_banner fl"></div>
    <div class="slogan fl">日夜兼程 · 急速送达</div>
    <div class="login_form fr">
      <div class="login_title clearfix">
        <h1>用户登录</h1>
        <a href="${pageContext.request.contextPath}/register">立即注册</a>
      </div>
      <div class="form_input">
        <form id="login_form">
          <input type="text" class="name_input" placeholder="请输入用户名" id="uname" value="${username}"/>
          <div class="user_error">输入错误</div>
          <input type="password" class="pass_input" placeholder="请输入密码" id="pwd"/>
          <div class="pwd_error">输入错误</div>
          <div class="more_input clearfix">
            <input type="checkbox" id="remember" checked="${remember}"/>
            <label>记住用户名</label>
            <a href="#">忘记密码</a>
          </div>
          <input type="submit" name="" value="登录" class="input_submit"/>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="footer no-mp" id="footer"></div>

</body>
</html>