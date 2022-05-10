<%--
  Created by IntelliJ IDEA.
  Author: Philipp Phan
  Date: 09.05.2022
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../stylesheets/elements.css">
    <link rel="stylesheet" href="../stylesheets/loginPage.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <base href="${pageContext.request.requestURI}"/>
</head>
<body>
    <main>

        <div class="login-card">
            <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo" class="logo img">
            <form action="../userManagement/servlets/LoginServlet.java" method="post">
                <input type="text" placeholder="Username" class="username-text">${pageScope.user.username}
                <input type="password" placeholder="Password" class="password">${pageScope.user.password}
                <button formaction="../ui/servlets/LoginServlet" formmethod="post" class="enter-button">Login</button>
            </form>
            <button class="cancel-button">Back</button>
            <!--<a href="../ui/RegistrationPage.html" class="linkRegistration">Not a user? Sign up!</a>-->
            <!--Get error Message from LoginBean-->
            <a type="text"></a>${sessionScope.loginBean.errorMessage}
        </div>
    </main>
</body>
</html>
