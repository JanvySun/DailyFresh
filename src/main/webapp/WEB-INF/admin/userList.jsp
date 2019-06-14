<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>用户信息管理</title>

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
      float:left;
    }
    #box{
      margin:10px 0 0 20px;
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

      $(function () {

          //给删除选中按钮添加单击事件
          $("#delSelected").click(function () {
              if (confirm("您确定要删除选中条目吗？")) {
                  var flag = false;
                  //判断是否有选中条目
                  var cbs = document.getElementsByName("uid");
                  for (var i = 0; i < cbs.length; i++) {
                      if (cbs[i].checked) {
                          //有一个条目选中了
                          flag = true;
                          break;
                      }
                  }

                  if (flag) {//有条目被选中
                      //表单提交
                      $("#form").submit();
                  }
              }
          });
          //1.获取第一个cb
          document.getElementById("firstCb").onclick = function () {
              //2.获取下边列表中所有的cb
              var cbs = document.getElementsByName("uid");
              //3.遍历
              for (var i = 0; i < cbs.length; i++) {
                  //4.设置这些cbs[i]的checked状态 = firstCb.checked
                  cbs[i].checked = this.checked;
              }
          }
      });
  </script>

</head>
<body>

<div id="box">
  <a><img src="${pageContext.request.contextPath}/images/logo02.png"></a>
</div>
<div class="reg_slogan">用户信息列表(管理页面)</div>

<div class="container">
  <h3 style="text-align: center"></h3>

  <div style="float: right;margin: 5px;">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/user/add">添加用户</a>
    <a class="btn btn-success" href="javascript:void(0);" id="delSelected">删除选中</a>
  </div>

  <form id="form" action="${pageContext.request.contextPath}/admin/user/delSelect" method="post">
    <table border="1" class="table table-bordered table-hover">
      <tr class="success">
        <th><input type="checkbox" id="firstCb"></th>
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱</th>
        <th>激活码</th>
        <th>是否激活</th>
        <th>管理员</th>
        <th>地址</th>
        <th>操作</th>
      </tr>

      <c:forEach items="${pb.list}" var="user" varStatus="s">
        <tr>
          <td><input type="checkbox" name="uid" value="${user.id}"></td>
          <td>${user.id}</td>
          <td>${user.username}</td>
          <td>${user.password}</td>
          <td>${user.email}</td>
          <td>${user.code}</td>
          <td>${user.status}</td>
          <td>${user.is_superuser}</td>
          <td>
            <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/admin/address/list/${user.id}">查看地址</a>
          </td>
          <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/admin/user/update/${user.id}">修改</a>&nbsp;
            <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a></td>
        </tr>
      </c:forEach>
    </table>
  </form>

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
            <a href="${pageContext.request.contextPath}/admin/user/list?page=${pb.currentPage - 1}"
               aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
        </c:if>

        <c:forEach begin="1" end="${pb.totalPage}" var="i">
          <c:if test="${pb.currentPage == i}">
            <li class="active">
              <a href="${pageContext.request.contextPath}/admin/user/list?page=${i}">${i}</a>
            </li>
          </c:if>
          <c:if test="${pb.currentPage != i}">
            <li><a
                href="${pageContext.request.contextPath}/admin/user/list?page=${i}">${i}</a>
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
            <a href="${pageContext.request.contextPath}/admin/user/list?page=${pb.currentPage + 1}"
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
