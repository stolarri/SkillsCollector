<%--
  Created by IntelliJ IDEA.
  User: mpsto
  Date: 18.10.2019
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Nieznane źródła</title>
</head>
<body>
<jsp:include page="fragments/headers.jsp"></jsp:include><br>
<h1>Źródła, których nie znasz: </h1>
<table border="3">
    <thead>
    <tr>
        <th>Lp.</th>
        <th>Nazwa Źródła</th>
        <th>Opis</th>
        <th>Akcja</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${unknownSrcs}" var="source" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${source.name}</td>
            <td>${source.description}</td>
            <td>Link</td>
        </tr>
    </c:forEach>

    </tbody>
</table><br>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
