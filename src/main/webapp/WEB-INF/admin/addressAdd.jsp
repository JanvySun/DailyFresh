<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>添加地址</title>

  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/address.js"></script>

  <style>
    .reg_slogan {
      text-align: center;
      font-size: 30px;
      color: #69a81e;
      margin-bottom: 30px;
    }
    #box{
      margin:20px 0 0 40px;
    }
  </style>

  <script>
      $(function () {
          $("#receiver").blur(check_receiver);
          $("#address").blur(check_addr);
          $("#zip_code").blur(check_zipCode);
          $("#phone").blur(check_phone);


          $('#add_form').submit(function () {
              // 表单提交时进行校验
              if (check_receiver() && check_addr() && check_zipCode() && check_phone()) {
                  $.post("${pageContext.request.contextPath}/admin/address/addHandle", $(this).serialize(),
                      function (data) {
                          if (data.flag==true){
                              location.href="${pageContext.request.contextPath}/admin/address/list/${uid}";
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
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">添加【${username}】的地址(管理页面)</div>

<div class="container" style="width:500px;">

  <form id="add_form">
    <div class="form-group">
      <label for="receiver">收件人：</label>
      <input type="text" class="form-control" id="receiver" name="name" placeholder="请输入收件人"/>
    </div>

    <div class="form-group">
      <label for="address">地址：</label>
      <input type="text" class="form-control" id="address" name="addr" placeholder="请输入地址"/>
    </div>

    <div class="form-group">
      <label for="zip_code">邮编：</label>
      <input type="text" class="form-control" id="zip_code" name="zipCode" placeholder="请输入邮编"/>
    </div>

    <div class="form-group">
      <label for="phone">手机号：</label>
      <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号"/>
    </div>

    <div class="form-group">
      <label>是否默认：</label>
      <input type="radio" name="status" value="Y"/>默认
      <input type="radio" name="status" value="N" checked="checked"/>不默认
    </div>

    <input name="uid" value="${uid}" type="hidden"/>

    <div class="form-group" style="text-align: center">
      <input class="btn btn-success" type="submit" value="提交"/>
      <input class="btn btn-default" type="reset" value="重置"/>
      <input class="btn btn-default" type="button" value="返回"/>
    </div>
  </form>

</div>
</body>
</html>