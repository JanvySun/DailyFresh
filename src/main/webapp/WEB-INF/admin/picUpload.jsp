<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>图片上传</title>

  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <style>
    .reg_slogan {
      /*height: 30px;*/
      text-align: center;
      font-size: 30px;
      color: #69a81e;
      /*margin: 10px 0 10px 150px;*/
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
<div class="reg_slogan">文件上传(管理页面)</div>

<div class="container" style="width: 400px;margin-top: 50px;">

  <form action="http://47.107.251.224/upload" enctype="multipart/form-data" method="post">
    <div class="form-group">
      <label for="picture">请选择文件：</label>
      <input type="file" name="pic" id="picture"/>
    </div>

    <div class="form-group" style="text-align: center">
      <input class="btn btn-success" type="submit" value="上传"/>
    </div>
  </form>

</div>
</body>
</html>