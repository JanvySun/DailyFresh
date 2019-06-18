<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>首页轮播图</title>

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
  <script>
      function deleteBanner(id) {
          //用户安全提示
          if (confirm("您确定要删除吗？")) {
              //访问路径
              location.href = "${pageContext.request.contextPath}/admin/indexBanner/del/" + id;
          }
      }
  </script>

</head>
<body>

<div id="box">
  <a href="${pageContext.request.contextPath}/admin/index">
    <img src="${pageContext.request.contextPath}/images/logo02.png">
  </a>
</div>
<div class="reg_slogan">首页轮播图列表(管理页面)</div>

<div class="container">

  <div style="float: right; margin-bottom: 5px;">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/indexBanner/add">添加Banner</a>
  </div>

  <table border="1" class="table table-bordered table-hover">
    <tr class="success">
      <th>图片</th>
      <th>展示顺序</th>
      <th>操作</th>
    </tr>

    <c:forEach items="${banner_list}" var="banner">
      <tr style="height: 108px;">
        <td><img src="${banner.image}" width="304px" height="108px"/></td>
        <td style="line-height: 108px">${banner.od}</td>
        <td style="line-height: 108px">
          <a class="btn btn-default btn-sm"
             href="${pageContext.request.contextPath}/admin/indexBanner/update/${banner.id}">修改</a>
          <a class="btn btn-default btn-sm" href="javascript:deleteBanner(${banner.id});">删除</a>
        </td>
      </tr>
    </c:forEach>
  </table>

</div>

</body>
</html>
