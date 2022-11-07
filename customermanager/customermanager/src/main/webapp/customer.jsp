<%@ page import="java.util.ArrayList" %>
<%@ page import="com.codegym.customermanager.model.Customer" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/11/2022
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <thead>
        <th>ID</th>
        <th>Ten</th>
        <th>Ngay Sinh</th>
        <th>Dia Chi</th>
        <th>Anh</th>
        <th>Gioi tinh</th>
        </thead>
        <tbody>
<%--            <%--%>
<%--                ArrayList<Customer> list = (ArrayList<Customer>) request.getAttribute("listCustomer");--%>

<%--                String str = "";--%>
<%--                for (Customer c : list) {--%>
<%--                    str += "<tr>";--%>
<%--                    str += "<td>" + c.getId() + "</td>";--%>
<%--                    str += "<td>" + c.getName() +"</td>";--%>
<%--                    str += "<td>" + c.getDayOfBirth() + "</td>";--%>
<%--                    str += "<td>" + c.getAddress()  + "</td>";--%>
<%--                    str += "<td>" + c.getImage() + "</td>";--%>
<%--                    str += "<td>" + c.getGender() + "</td>";--%>
<%--                    str += "</tr>";--%>
<%--                }--%>
<%--                out.println(str);--%>
<%--            %>--%>



        <c:forEach var="c" items="${requestScope.listCustomer}">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${c.getId() <3}">
                            ${c.getId()}
                        </c:when>
                        <c:otherwise>
                            3
                        </c:otherwise>
                    </c:choose>

                </td>
                <td>
                       <a href="/customer?action=edit&id=${c.getId()}"> ${c.getName()}</a>
                </td>
                <td>${c.getDayOfBirth()}</td>
                <td>${c.getAddress()}</td>
                <td>${c.getImage()}</td>
                <td>
                    <c:forEach items="${requestScope.listGender}" var="gender">
                        <c:if test="${gender.getId()==c.getGender()}">
                            ${gender.getName()}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</body>
</html>
