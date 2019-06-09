<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script>
    $(function () {
        if("${user}" == ""){
            $("#user_wec").hide();
            $("#login_wec").show();
        } else {
            $("#login_wec").hide();
            $("#user_wec").show();
        }

        $("#logout").click(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/user/logoutHandle",
                type:"post",
                dataType:"json",
                success : function (data) {
                    location.href="${pageContext.request.contextPath}/index";
                }
            });
            return false;
        });
    });
</script>
<div class="welcome fl">欢迎来到天天生鲜!</div>
<div class="fr">
  <div class="login_btn fl" id="user_wec">
    欢迎您：<em>${user.username}</em>
    <span>|</span>
    <a href="" id="logout">退出</a>
  </div>
  <div class="login_btn fl" id="login_wec">
    <a href="${pageContext.request.contextPath}/login">登录</a>
    <span>|</span>
    <a href="${pageContext.request.contextPath}/register">注册</a>
  </div>
  <div class="user_link fl">
    <span>|</span>
    <a href="${pageContext.request.contextPath}/user/info">用户中心</a>
    <span>|</span>
    <a href="../../cart.html">我的购物车</a>
    <span>|</span>
    <a href="userCenterOrder.jsp">我的订单</a>
  </div>
</div>
