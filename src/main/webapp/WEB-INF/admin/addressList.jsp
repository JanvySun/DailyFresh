<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>用户地址管理</title>

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
      function deleteAddr(id) {
          //用户安全提示
          if (confirm("您确定要删除吗？")) {
              //访问路径
              location.href = "${pageContext.request.contextPath}/admin/address/del/" + id;
          }
      }

      $(function () {

          if ("${addr_list}" == "[]") {
              alert("该用户无地址");
          }

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
  <a href="${pageContext.request.contextPath}/admin/index">
    <img src="${pageContext.request.contextPath}/images/logo02.png">
  </a>
</div>
<div class="reg_slogan">用户【${username}】地址列表(管理页面)</div>

<div class="container">

  <div style="float: right;margin: 5px;">
    <a class="btn btn-success" href="${pageContext.request.contextPath}/admin/address/add?uname=${username}">添加地址</a>
    <a class="btn btn-success" href="javascript:void(0);" id="delSelected">删除选中</a>
  </div>

  <form id="form" action="${pageContext.request.contextPath}/admin/address/delSelect" method="post">
    <table border="1" class="table table-bordered table-hover">
      <tr class="success">
        <th><input type="checkbox" id="firstCb"></th>
        <th>收件人</th>
        <th>地址</th>
        <th>邮编</th>
        <th>电话</th>
        <th>是否默认</th>
        <th>操作</th>
      </tr>

      <c:forEach items="${addr_list}" var="addr">
        <tr>
          <td><input type="checkbox" name="uid" value="${addr.id}"></td>
          <td>${addr.receiver}</td>
          <td>${addr.addr}</td>
          <td>${addr.zip_code}</td>
          <td>${addr.phone}</td>
          <td>${addr.is_default}</td>
          <td><a class="btn btn-default btn-sm"
                 href="${pageContext.request.contextPath}/admin/address/update/${addr.id}">修改</a>&nbsp;
            <a class="btn btn-default btn-sm" href="javascript:deleteAddr(${addr.id});">删除</a></td>
        </tr>
      </c:forEach>
    </table>
  </form>

</div>

</body>
</html>
