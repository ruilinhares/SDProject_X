<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 19/12/2017
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hey</title>
</head>
<body>
    <c:choose>
        <c:when test="${session.loggedin == true}">
            <p>Welcome, ${session.username}. Say HEY to someone.</p>
        </c:when>
        <c:otherwise>
            <p>Welcome, anonymous user. Say HEY to someone.</p>
        </c:otherwise>
    </c:choose>

    <c:forEach items="${iVotasBean.allUsers}" var="value">
        <c:out value="${value}" /><br>
    </c:forEach>

    <p><a href="<s:url action="index" />">Start</a></p>
</body>
</html>
