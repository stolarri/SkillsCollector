<%--
  Created by IntelliJ IDEA.
  User: mpsto
  Date: 18.10.2019
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Potwierdź źródło</title>
</head>
<body>
<jsp:include page="fragments/headers.jsp"></jsp:include><br>
<h1>Źródła, których nie znasz: </h1>
<table border="3">
    <thead>
    <tr>
        <th>Opis</th>
        <th>Akcja</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sources}" var="source" varStatus="stat">
        <c:if test="${source.id == Id}">
            <tr>
                <td>${source.description}</td>
                <td>
                    <form method="post" action="/user/unknown-sources">
                        <button type="submit">Na pewno potwierdzasz?</button>
                        <input type="hidden" name="confirmedSource" value="${source.id}"/>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>

    </tbody>
</table><br>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
