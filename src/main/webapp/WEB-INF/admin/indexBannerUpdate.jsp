<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>修改首页轮播图</title>

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
      $(function () {
          $("#update_form").submit(function () {
              // 不让表单提交，自己进行处理
              $.post("${pageContext.request.contextPath}/admin/indexBanner/updateHandle", $(this).serialize(), function (data) {
                  if (data.flag == true) {
                      location.href = "${pageContext.request.contextPath}/admin/indexBanner/list";
                  } else {
                      alert(data.message);
                  }
              });
              return false;
          });
          $("#image_url").blur(function () {
              $("#image").prop("src", $("#image_url").val());
          });
      });
  </script>

</head>
<body>

<div id="box">
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">修改首页轮播图(管理页面)</div>

<div class="container" style="width: 500px;">

  <form id="update_form">
    <!--  隐藏域 提交id-->
    <input type="hidden" name="id" value="${banner.id}">
    <input type="hidden" name="sku_id" value="${banner.sku_id}">

    <div class="form-group">
      <label>展示顺序：</label>
      <input type="text" class="form-control" value="${banner.od}" name="od"/>
    </div>

    <div class="form-group">
      <label>图片url：</label>
      <input type="text" class="form-control" value="${banner.image}" name="image" id="image_url"/>
    </div>

    <div class="form-group">
      <label>图片展示：</label>
      <img src="${banner.image}" width="304px" height="108px" id="image">
      <a target="_blank" href="${pageContext.request.contextPath}/admin/pictureUpload">上传图片</a>
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