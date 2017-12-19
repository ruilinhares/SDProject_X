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
<link rel="stylesheet" href="../CSS/style.css" />
<body>
<form>
  <div class="imgcontainer">
    <img src="../assets/image.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
    <label><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>

    <button type="submit">Login</button>
    <label><b>Ou</b></label>
    <button id="face">Login with Facebook</button>
  </div>
</form>
</body>
</html>
