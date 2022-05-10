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

        <form class="login-card" action="../jsp/login.jsp" method="post">
            <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo" class="logo img">
                <input type="text" placeholder="Username" class="username" required>${pageScope.user.username}
                <input type="password" placeholder="Password" class="password" required>${pageScope.user.password}
                <!--Get error Message from LoginBean-->
                <jsp:useBean class="com.esports.manager.userManagement.beans.LoginBean" id="LoginBean" scope="page">
                    <jsp:getProperty name="LoginBean" property="errorMessage"/>
                </jsp:useBean>
            <button class="enter-button">Login</button>
            <button class="cancel-button">Back</button>
            <!--<a href="../ui/RegistrationPage.html" class="linkRegistration">Not a user? Sign up!</a>-->
        </form>
    </main>
</body>
</html>
