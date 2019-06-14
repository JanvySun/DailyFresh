<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>修改商品SKU</title>

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
              $.post("${pageContext.request.contextPath}/admin/goods_sku/updateHandle", $(this).serialize(), function (data) {
                  if (data.flag == true) {
                      location.href = "${pageContext.request.contextPath}/admin/goods_sku/list/${sku.goods_id}";
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
<div class="reg_slogan">修改商品SKU(管理页面)</div>

<div class="container" style="width: 500px;">

  <form id="update_form">
    <!--  隐藏域 提交id-->
    <input type="hidden" name="id" value="${sku.id}">
    <input type="hidden" name="goods_id" value="${sku.goods_id}">
    <input type="hidden" name="type_id" value="${sku.type_id}">

    <div class="form-group">
      <label>商品名称：</label>
      <input type="text" class="form-control" name="name" value="${sku.name}"/>
    </div>

    <div class="form-group">
      <label>商品描述：</label>
      <input type="text" class="form-control" name="desc" value="${sku.desc}"/>
    </div>

    <div class="form-group">
      <label>商品价格：</label>
      <input type="text" class="form-control" name="price" value="${sku.price}"/>
    </div>

    <div class="form-group">
      <label>商品单位：</label>
      <input type="text" class="form-control" name="unite" value="${sku.unite}"/>
    </div>

    <div class="form-group">
      <label>库存：</label>
      <input type="text" class="form-control" name="stock" value="${sku.stock}"/>
    </div>
    <div class="form-group">
      <label>销量：</label>
      <input type="text" class="form-control" name="sales" value="${sku.sales}"/>
    </div>
    <div class="form-group">
      <label>状态：</label>
      <input type="text" class="form-control" name="status" value="${sku.status}"/>
    </div>

    <div class="form-group">
      <label>图片url：</label>
      <input type="text" class="form-control" value="${sku.image}" name="image" id="image_url"/>
    </div>

    <div class="form-group">
      <label>图片展示：</label>
      <img src="${sku.image}" id="image" width="100px" height="100px">
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