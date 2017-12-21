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
    <title>iVotas</title>
    <link rel="stylesheet" href="CSS/style.css" />
</head>
<body>
    <form action="login.action" method="post">
        <div class="imgcontainer">
            <img src="assets/image.png" >
        </div>

        <div class="container">
            <label><b>Username</b></label>
            <s:textfield name="username" />

            <label><b>Password</b></label>
            <s:textfield name="password" />

            <button type="submit" value="login">Login</button>
            <label><b>Ou</b></label>
            <button id="face">Login with Facebook</button>
        </div>
    </form>
</body>
</html>
