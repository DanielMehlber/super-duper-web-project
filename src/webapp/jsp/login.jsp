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
    <base href="${pageContext.request.requestURI}" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../stylesheets/elements.css">
    <link rel="stylesheet" href="../stylesheets/loginPage.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
    <main>
        <form class="login-card" action="../login" method="POST">
            <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo" class="logo img">
                <input name="username" type="text" placeholder="Username" class="username" required>${requestScope.username}
                <input name="password" type="password" placeholder="Password" class="password" required>${requestScope.password}
                <!--Get error Message from LoginBean-->
                <c:if test="${not empty requestScope.errorMessage}">    
                	<em>${errorMessage}</em>
               	</c:if>
            <button class="enter-button">Login</button>
            <button class="cancel-button">Back</button>
        </form>
    </main>
</body>
</html>
