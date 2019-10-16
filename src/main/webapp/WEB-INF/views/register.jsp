<%--
  Created by IntelliJ IDEA.
  User: mpsto
  Date: 13.10.2019
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formularz rejestracyjny</title>
</head>
<body>
<c:if test="${error != null}">
    <p style="color: red">Taki użytkownik już istnieje, wybierz inną nazwę!</p><br>
</c:if>
<jsp:include page="fragments/headers.jsp"></jsp:include><br>
<h1>Formularz rejestracyjny</h1>
<div>
    <form method="post" action="/register">
        <table>
            <tr>
                <td><label>Login</label></td>
                <td><input type="text" name="username" /></td>
            </tr>
            <tr>
                <td><label>Hasło</label></td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td><label>Imię</label></td>
                <td><input type="text" name="firstName" /></td>
            </tr>
            <tr>
                <td><label>Nazwisko</label></td>
                <td><input type="text" name="lastName" /></td>
            </tr>
            <tr>
                <td><input type="submit" value="Rejestruj"/></td>
            </tr>
        </table>
    </form>
</div><br><br>
<jsp:include page="fragments/footer.jsp"></jsp:include>
</body>
</html>
