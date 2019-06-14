<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>商品SPU管理</title>

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
      margin: 15px 0 5px 150px;
      float: left;
    }

    #box {
      margin: 10px 0 0 20px;
    }
  </style>

  <script>
      function deleteUser(id) {
          //用户安全提示
          if (confirm("您确定要删除吗？")) {
              //访问路径
              location.href = "${pageContext.request.contextPath}/admin/user/del/" + id;
          }
      }
  </script>

</head>
<body>

<div id="box">
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">商品SPU列表(管理页面)</div>

<div class="container">
  <h3 style="text-align: center"></h3>

  <div style="float: right;margin: 5px;">
    <a class="btn btn-success" href="#">添加商品</a>
  </div>

  <table border="1" class="table table-bordered table-hover">
    <tr class="success">
      <th>ID</th>
      <th>商品名称</th>
      <th>商品详情</th>
      <th>商品对应SKU</th>
      <th>操作</th>
    </tr>

    <c:forEach items="${pb.list}" var="goods" varStatus="s">
      <tr>
        <td>${goods.id}</td>
        <td>${goods.name}</td>
        <td>${goods.detail}</td>
        <td>
          <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/admin/goods_sku/list/${goods.id}">查看对应SKU</a>
        </td>
        <td><a class="btn btn-default btn-sm"
               href="#">修改</a>&nbsp;
          <a class="btn btn-default btn-sm" href="#">删除</a></td>
      </tr>
    </c:forEach>
  </table>

  <div>
    <nav aria-label="Page navigation">
      <ul class="pagination">
        <c:if test="${pb.currentPage == 1}">
          <li class="disabled">
            <a href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
        </c:if>
        <c:if test="${pb.currentPage != 1}">
          <li>
            <a href="${pageContext.request.contextPath}/admin/goods/list?page=${pb.currentPage - 1}"
               aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
        </c:if>

        <c:forEach begin="1" end="${pb.totalPage}" var="i">
          <c:if test="${pb.currentPage == i}">
            <li class="active">
              <a href="${pageContext.request.contextPath}/admin/goods/list?page=${i}">${i}</a>
            </li>
          </c:if>
          <c:if test="${pb.currentPage != i}">
            <li><a
                href="${pageContext.request.contextPath}/admin/goods/list?page=${i}">${i}</a>
            </li>
          </c:if>
        </c:forEach>

        <c:if test="${pb.currentPage == pb.totalPage}">
          <li class="disabled">
            <a href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </c:if>

        <c:if test="${pb.currentPage != pb.totalPage}">
          <li>
            <a href="${pageContext.request.contextPath}/admin/goods/list?page=${pb.currentPage + 1}"
               aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </c:if>

        <span style="font-size: 25px;margin-left: 5px;">
          共${pb.totalCount}条记录，共${pb.totalPage}页
        </span>
      </ul>
    </nav>
  </div>
</div>

</body>
</html>
