<%--
  Created by IntelliJ IDEA.
  User: mpsto
  Date: 17.10.2019
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Skille użytkownika</title>
</head>
<body>
    <jsp:include page="fragments/headers.jsp"></jsp:include><br>
    <h1>Twoje skille</h1>
    <table border="3">
        <thead>
        <tr>
            <th>Lp.</th>
            <th>Nazwa Skilla</th>
            <th>Poziom</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${skills}" var="skill" varStatus="stat">
            <tr>
            <td>${stat.count}</td>
            <td>${skill.key.name}</td>
            <td>${skill.value}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table><br>
    <jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
