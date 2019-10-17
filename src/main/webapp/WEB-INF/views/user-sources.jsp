<%--
  Created by IntelliJ IDEA.
  User: mpsto
  Date: 17.10.2019
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Znane źródła</title>
</head>
<body>
<jsp:include page="fragments/headers.jsp"></jsp:include><br>
<h1>Źródła, które znasz: </h1>
<table border="3">
    <thead>
    <tr>
        <th>Lp.</th>
        <th>Nazwa Źródła</th>
        <th>Opis</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${knownSrcs}" var="source" varStatus="stat">
        <tr>
            <td>${stat.count}</td>
            <td>${source.name}</td>
            <td>${source.description}</td>
        </tr>
    </c:forEach>

    </tbody>
</table><br>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
