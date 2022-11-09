<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/11/2022
  Time: 10:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/translate">
      <label>Nhập từ cần dịch</label> <br>
<%--        <input type="hidden" name="action" value="dich">--%>
      <input name="keyword" placeholder="" value="${requestScope.keyword}"> <br>
        <c:if test="${requestScope.ketqua !=null}">
            <label>Ket qua: ${requestScope.ketqua}</label> <br>
        </c:if>
      <button type="submit">Dịch</button>
    </form>
</body>
</html>
