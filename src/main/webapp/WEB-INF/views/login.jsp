<%--
  Created by IntelliJ IDEA.
  User: mpsto
  Date: 16.10.2019
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logowanie</title>
</head>
<body>
    <h1>Logowanie</h1><br>
    <div>
        <form method="post" action="login">
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
                    <td><input type="submit" value="Zaloguj" /></td>
                </tr>
            </table>
        </form>
    </div>
    <c:if test="${error != null}">
        <p style="color: red">${error}</p>
    </c:if>
</body>
</html>
