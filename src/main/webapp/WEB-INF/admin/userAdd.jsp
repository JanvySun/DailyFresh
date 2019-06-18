<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>添加用户</title>

  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

  <style>
    .reg_slogan {
      text-align: center;
      font-size: 30px;
      color: #69a81e;
      margin-bottom: 30px;
    }

    #box {
      margin: 20px 0 0 40px;
    }
  </style>

  <script>
      function check_username() {
          var $username = $("#username");
          var reg_username = /^\w{5,20}$/;
          var flag = reg_username.test($username.val());
          if (flag) {
              // 匹配成功，用户名合法
              $username.css("border", "");
          }
          else {
              // 匹配失败，用户名不合法
              $username.css("border", "1px solid red");
          }
          return flag;
      }

      function check_pwd() {
          var $pwd = $('#password');
          var reg_pwd = /^\w{5,20}$/;
          var flag = reg_pwd.test($pwd.val());
          if (flag) {
              $pwd.css("border", "");
          } else {
              // 不匹配
              $pwd.css("border", "1px solid red");
          }
          return flag;
      }

      function check_email() {
          var $email = $('#email');
          var re_email = /^[a-z0-9][\w+.\-]*@[a-z0-9\-]+(\.[a-z]{2,5}){1,2}$/;
          var flag = re_email.test($email.val());
          if (flag) {
              $email.css("border", "");
          }
          else {
              $email.css("border", "1px solid red");
          }
          return flag;
      }

      $(function () {
          $("#username").blur(check_username);
          $("#password").blur(check_pwd);
          $("#email").blur(check_email);


          $('#add_form').submit(function () {
              // 表单提交时进行校验
              if (check_username() && check_pwd() && check_email()) {
                  $.post("${pageContext.request.contextPath}/admin/user/addHandle", $(this).serialize(),
                      function (data) {
                          if (data.flag == true) {
                              location.href = "${pageContext.request.contextPath}/admin/user/list";
                          } else {
                              alert(data.message);
                          }
                      });
              }
              // 不让表单提交，自行处理
              return false;
          });

      });
  </script>

</head>
<body>

<div id="box">
  <a href="${pageContext.request.contextPath}/admin/index">
    <img src="${pageContext.request.contextPath}/images/logo02.png">
  </a>
</div>
<div class="reg_slogan">添加用户(管理页面)</div>

<div class="container" style="width:500px;">

  <form id="add_form">
    <div class="form-group">
      <label for="username">用户名：</label>
      <input type="text" class="form-control" id="username" name="name" placeholder="请输入用户名"/>
    </div>

    <div class="form-group">
      <label for="password">密码：</label>
      <input type="password" class="form-control" id="password" name="pwd" placeholder="请输入密码"/>
    </div>

    <div class="form-group">
      <label for="email">邮箱：</label>
      <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱"/>
    </div>

    <div class="form-group">
      <label>是否激活：</label>
      <input type="radio" name="status" value="Y" checked="checked"/>激活
      <input type="radio" name="status" value="N"/>未激活
    </div>

    <div class="form-group" style="text-align: center">
      <input class="btn btn-success" type="submit" value="提交"/>
      <input class="btn btn-default" type="reset" value="重置"/>
      <input class="btn btn-default" type="button" value="返回"/>
    </div>
  </form>

</div>
</body>
</html>