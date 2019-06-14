<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>商品SKU管理</title>

  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <style type="text/css">
    td, th {
      text-align: center;
    }

    .reg_slogan {
      height: 30px;
      text-align: left;
      font-size: 24px;
      color: #69a81e;
      margin: 15px 0 0 150px;
    }

    #box {
      margin: 10px 0 0 20px;
    }
  </style>

  <script>
      $(function () {
          if ("${sku_list}" == "[]") {
              alert("该商品未添加SKU");
          }
      });
  </script>

</head>
<body>

<div id="box">
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">商品【${goodsName}】SKU列表(管理页面)</div>

<div class="container">

  <div style="float: right;margin: 5px;">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/goods/list">返回列表</a>
    <a class="btn btn-success" href="#">添加SKU</a>
  </div>

  <table border="1" class="table table-bordered table-hover">
    <tr class="success">
      <th>商品名称</th>
      <th>商品描述</th>
      <th>商品价格</th>
      <th>商品单位</th>
      <th>图片</th>
      <th>库存</th>
      <th>销量</th>
      <th>状态</th>
      <th>操作</th>
    </tr>

    <c:forEach items="${sku_list}" var="sku">
      <tr style="height:100px;">
        <td style="line-height: 100px">${sku.name}</td>
        <td style="line-height: 100px">${sku.desc}</td>
        <td style="line-height: 100px">${sku.price}</td>
        <td style="line-height: 100px">${sku.unite}</td>
        <td><img src="${sku.image}" width="100px" height="100px"/></td>
        <td style="line-height: 100px">${sku.stock}</td>
        <td style="line-height: 100px">${sku.sales}</td>
        <td style="line-height: 100px">${sku.status}</td>
        <td style="line-height: 100px"><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/admin/goods_sku/update/${sku.id}">修改</a>&nbsp;
          <a class="btn btn-default btn-sm" href="#">删除</a></td>
      </tr>
    </c:forEach>
  </table>

</div>

</body>
</html>
