<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
  <meta charset="UTF-8">
  <title>天天生鲜-购物车</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script>
      // 计算被选中的商品的总件数和总价格
      function cal_cart_info() {
          var total_count = 0;
          var total_amount = 0;
          $(".cart_list_td").find(":checked").parents('ul').each(function () {
              var count = $(this).find('.num_show').val();
              var amount = $(this).children('.col07').text();
              total_count += parseInt(count);
              total_amount += parseFloat(amount);
          });
          $(".settlements").find('em').text(total_amount.toFixed(2));
          $(".settlements").find("b").text(total_count);
      }

      // 计算商品小计
      function cal_goods_amount(sku_ul) {
          // 获取商品的价格和数量
          var count = sku_ul.find('.num_show').val();
          var price = sku_ul.children('.col05').text();
          // 计算商品小计
          var amount = parseInt(count) * parseFloat(price);
          // 设置商品小计
          sku_ul.children('.col07').text(amount.toFixed(2) + "元");
      }

      $(function () {
          $.get("${pageContext.request.contextPath}/header", function (data) {
              $("#header").html(data);
          });
          $.get("${pageContext.request.contextPath}/footer", function (data) {
              $("#footer").html(data);
          });

          cal_cart_info();

          // 商品的全选和全不选
          $(".settlements").find(":checkbox").change(function () {
              // 获取全选的checkbox的选中状态
              var is_checked = $(this).prop('checked');
              // 遍历商品对应的checkbox，设置这些checkbox的选中状态和父级保持一致
              $('.cart_list_td').find(":checkbox").each(function () {
                  $(this).prop("checked", is_checked);
              });
              cal_cart_info();
          });

          // 商品对应的checkbox状态改变时，设置全选checkbox 的状态
          $(".cart_list_td").find(":checkbox").change(function () {
              // 获取页面上所有商品的数目
              var all_len = $(".cart_list_td").length;
              // 获取页面上被选中的商品的数目
              var checked_len = $('.cart_list_td').find(":checked").length;
              if (checked_len < all_len) {
                  $(".settlements").find(':checkbox').prop("checked", false);
              } else {
                  $(".settlements").find(':checkbox').prop("checked", true);
              }
              cal_cart_info();
          });

          // 购物车商品数量的增加
          $(".add").click(function () {
              // 获取商品的id和数量
              var $this = $(this);
              var count = $this.next().val();
              var sku_id = $this.next().prop("name");
              count = parseInt(count) + 1;
              var params = {"skuId": sku_id, "count": count};
              var total = 0;
              $.post("${pageContext.request.contextPath}/cart/update", params, function (data) {
                  if (data.flag == true) {
                      // 更新成功
                      total = data.obj;
                      // 重新设置商品的数目
                      $this.next().val(count);
                      cal_goods_amount($this.parents("ul"));
                      // 获取商品对应的checkbox的选中状态
                      var is_checked = $this.parents("ul").find(":checkbox").prop("checked");
                      if (is_checked) {
                          cal_cart_info();
                      }
                      // 更新商品总件数
                      $(".total_count").children("em").text(total);
                  } else {
                      alert(data.message);
                  }
              });
          });

          // 购物车商品数量的减少
          $(".minus").click(function () {
              // 获取商品的id和数量
              var $this = $(this);
              var count = $this.prev().val();
              var sku_id = $this.prev().prop("name");
              count = parseInt(count) - 1;
              if (count <= 0) {
                  return;
              }
              var params = {"skuId": sku_id, "count": count};
              var total = 0;
              $.post("${pageContext.request.contextPath}/cart/update", params, function (data) {
                  if (data.flag == true) {
                      // 更新成功
                      total = data.obj;
                      // 重新设置商品的数目
                      $this.prev().val(count);
                      cal_goods_amount($this.parents("ul"));
                      // 获取商品对应的checkbox的选中状态
                      var is_checked = $this.parents("ul").find(":checkbox").prop("checked");
                      if (is_checked) {
                          cal_cart_info();
                      }
                      // 更新商品总件数
                      $(".total_count").children("em").text(total);
                  } else {
                      alert(data.message);
                  }
              });
          });

          // 输入框之前的数据
          var pre_count = 0;
          $(".num_show").focus(function () {
              pre_count = $(this).val();
          });
          // 购物车商品数量的手动输入
          $(".num_show").blur(function () {
              // 获取商品的id和数量
              var $this = $(this);
              var count = $this.val();
              var sku_id = $this.prop("name");
              // 校验参数
              if (isNaN(count) || count.trim().length == 0 || parseInt(count) <= 0) {
                  // 输入的数据非法，还原为之前的
                  $this.val(pre_count);
                  return;
              }
              count = parseInt(count);
              var params = {"skuId": sku_id, "count": count};
              var total = 0;
              $.post("${pageContext.request.contextPath}/cart/update", params, function (data) {
                  if (data.flag == true) {
                      // 更新成功
                      total = data.obj;
                      // 重新设置商品的数目
                      $this.prev().val(count);
                      cal_goods_amount($this.parents("ul"));
                      // 获取商品对应的checkbox的选中状态
                      var is_checked = $this.parents("ul").find(":checkbox").prop("checked");
                      if (is_checked) {
                          cal_cart_info();
                      }
                      // 更新商品总件数
                      $(".total_count").children("em").text(total);
                  } else {
                      alert(data.message);
                      $this.val(pre_count);
                  }
              });
          });

          // 删除购物车
          $(".cart_list_td").children(".col08").children('a').click(function () {
              var $this = $(this);
              var sku_id = $this.parents('ul').find('.num_show').prop("name");
              var params = {'skuId':sku_id};
              var sku_ul = $this.parents('ul');
              var total = 0;
              $.post("${pageContext.request.contextPath}/cart/delete", params, function (data) {
                  if(data.flag == true){
                      // 删除成功
                      total = data.obj;
                      sku_ul.remove();
                      // 获取sku_ul的选中状态
                      var is_checked = sku_ul.find(":checkbox").prop("checked");
                      if(is_checked){
                          cal_cart_info();
                      }
                      $(".total_count").children("em").text(total);
                  } else {
                      alert(data.message);
                  }
              })
          });
      });
  </script>

</head>
<body>

<div class="header_con">
  <div class="header" id="header"></div>
</div>

<div class="search_bar clearfix">
  <a href="${pageContext.request.contextPath}/" class="logo fl">
    <img src="${pageContext.request.contextPath}/images/logo.png"></a>
  <div class="sub_page_name fl">|&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
  <div class="search_con fr">
    <form method="post" action="${pageContext.request.contextPath}/find">
      <input type="text" class="input_text fl" name="findName" placeholder="搜索商品">
      <input type="submit" class="input_btn fr" value="搜索">
    </form>
  </div>

</div>

<div class="total_count">全部商品<em>${count}</em>件</div>
<ul class="cart_list_th clearfix">
  <li class="col01">商品名称</li>
  <li class="col02">商品单位</li>
  <li class="col03">商品价格</li>
  <li class="col04">数量</li>
  <li class="col05">小计</li>
  <li class="col06">操作</li>
</ul>

<c:forEach items="${carts}" var="cart">
  <ul class="cart_list_td clearfix">
    <li class="col01"><input type="checkbox" name="" checked></li>
    <li class="col02"><img src="${cart.sku.image}"></li>
    <li class="col03">${cart.sku.name}<br><em>${cart.sku.price}元/${cart.sku.unite}</em></li>
    <li class="col04">${cart.sku.unite}</li>
    <li class="col05">${cart.sku.price}元</li>
    <li class="col06">
      <div class="num_add">
        <a href="javascript:void(0);" class="add fl">+</a>
        <input type="text" name="${cart.sku.id}" class="num_show fl" value="${cart.count}">
        <a href="javascript:void(0);" class="minus fl">-</a>
      </div>
    </li>
    <li class="col07">${cart.amount}元</li>
    <li class="col08"><a href="javascript:void(0);">删除</a></li>
  </ul>
</c:forEach>

<ul class="settlements">
  <li class="col01"><input type="checkbox" name="" checked=""></li>
  <li class="col02">全选</li>
  <li class="col03">合计(不含运费)：<span>¥</span><em>0.00</em><br>共计<b>0</b>件商品</li>
  <li class="col04"><a href="../../place_order.html">去结算</a></li>
</ul>

<div class="footer" id="footer"></div>

</body>
</html>