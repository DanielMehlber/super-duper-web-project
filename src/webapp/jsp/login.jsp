<%--
  Created by IntelliJ IDEA.
  Author: Philipp Phan
  Date: 09.05.2022
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--<link rel="stylesheet" href="../ui/elements.css">
    <link rel="stylesheet" href="../ui/loginPage.css">-->
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <base href="${pageContext.request.requestURI}"/>
</head>
<body>
    <main>

        <div class="login-card">
            <form action="../servlets/LoginServlet" method="post">
                <input type="text" placeholder="Username" required="required" class="username-text">${pageScope.user.username}>
                <input type="password" placeholder="password" required="required" class="password">${pageScope.user.password}>
                <button formaction="../ui/servlets/LoginServlet" formmethod="post" class="enter-button">Login</button>
            </form>
            <button class="cancel-button">Back</button>
            <!--<a href="../ui/RegistrationPage.html" class="linkRegistration">Not a user? Sign up!</a>-->
            <a type="text">Incorrect username or password</a>
        </div>
    </main>
</body>
</html>
