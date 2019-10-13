<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: mpsto
  Date: 13.10.2019
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<footer>
    <div>
        <span>Autor: Michał Stolarek  </span>
        <span>Rok: <%= LocalDateTime.now().getYear()%></span>
    </div>
</footer>
