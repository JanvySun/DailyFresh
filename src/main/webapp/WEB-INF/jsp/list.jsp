<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>List</title>
</head>
<body>

<h3>查询所有账户</h3>

<c:forEach items="${list}" var="account">
  ${account.name} &nbsp; ${account.money} <br/>
</c:forEach>

</body>
</html>