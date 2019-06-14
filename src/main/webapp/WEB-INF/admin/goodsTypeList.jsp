<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>商品类型管理</title>

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
      margin: 15px 0 20px 150px;
    }

    #box {
      margin: 10px 0 0 20px;
    }
  </style>

</head>
<body>

<div id="box">
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">商品种类列表(注：该表只能修改其图片url)</div>

<div class="container">

  <table border="1" class="table table-bordered table-hover">
    <tr class="success">
      <th>种类名称</th>
      <th>logo类名(用于控制logo展示)</th>
      <th>图片</th>
      <th>操作</th>
    </tr>

    <c:forEach items="${goods_types}" var="type">
      <tr style="height: 150px;">
        <td style="line-height:150px">${type.name}</td>
        <td style="line-height: 150px">${type.logo}</td>
        <td><img src="${type.image}" width="100px" height="150px"/></td>
        <td style="line-height: 150px">
          <a class="btn btn-default btn-sm"
             href="${pageContext.request.contextPath}/admin/goodsType/update/${type.id}">修改</a>
        </td>
      </tr>
    </c:forEach>
  </table>

</div>

</body>
</html>
