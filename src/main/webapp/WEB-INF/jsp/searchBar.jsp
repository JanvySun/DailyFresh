<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<a href="${pageContext.request.contextPath}/" class="logo fl"><img src="${pageContext.request.contextPath}/images/logo.png"></a>
<div class="search_con fl">
  <form method="post" action="${pageContext.request.contextPath}/find">
    <input type="text" class="input_text fl" name="findName" placeholder="搜索商品">
    <input type="submit" class="input_btn fr" value="搜索">
  </form>
</div>
<div class="guest_cart fr">
  <a href="${pageContext.request.contextPath}/user/cart" class="cart_name fl" id="cart">我的购物车</a>
  <div class="goods_count fl">${cart_count}</div>
</div>
