<!--@Author Philipp Phan-->
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.contextPath}/"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="stylesheets/Elements.css">
    <link rel="stylesheet" href="stylesheets/loginPage.css">
    <script src="js/cookieCheck.js" defer></script>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
<main>
    <form class="login-card" action="login" method="POST">
        <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo"
             class="logo img">
        <input name="username" type="text" placeholder="Username" class="username"
               value="${requestScope.loginBean.username}" required/>
        <input name="password" type="password" placeholder="Password" class="password" required/>
        <!--Get error Message from LoginBean-->
        <p class="error-text">
            <c:if test="${not empty requestScope.loginBean.errorMessage}">
                ${requestScope.loginBean.errorMessage}
            </c:if>
        </p>
        <button class="enter-button" type="submit">Login</button>
        <button class="cancel-button" type="reset">Back</button>
        <a class="registration-redirect" href="registration">Not a member yet?</a>
    </form>
    <div id="cookieText"></div>
    </main>
    <noscript><%@include file="fragments/javascript-deactivated.jspf" %></noscript>
</body>
</html>
