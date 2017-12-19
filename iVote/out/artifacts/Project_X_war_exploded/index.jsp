<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 18/12/2017
  Time: 03:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Hey!</title>
</head>
<body>
    <s:form action="login" method="post">
      <s:text name="Username:" />
      <s:textfield name="username" /><br>
      <s:submit />
    </s:form>
</body>
</html>
