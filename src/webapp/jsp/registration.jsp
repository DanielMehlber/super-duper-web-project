<%--
  Author: Maximilian Rublik
  Date: 10.05.2022
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration</title>
        <meta charset="UTF-8">
    	<base href="${pageContext.request.requestURI}" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/Elements.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/RegistrationPage.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/registration.js"></script>
        <noscript>JavaScript not enabled</noscript>
        <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    </head>
    <body>
        <main>
        	<form class="registration-card" method="POST" action="${pageContext.request.contextPath}/registration">
                <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo" class="logo img">
                <input type="email" name="email" class="email" placeholder="Choose E-Mail" maxlength="40" required>
                <input type="text" name="username" class="username" placeholder="Choose Username" maxlength="30" required>
                <input type="password" name="password" class="password" minlength="8" onkeyup="checkPassword()" placeholder="Choose Password" required>
                <input type="password" name="confirmPassword" class="confirmPassword" onkeyup="checkPassword()" minlength="8" placeholder="Confirm Password" required>
                
                <c:if test="${not empty requestScope.registrationBean.errorMessage}">
                	<div class="error-message-text">${requestScope.registrationBean.errorMessage}</div>
               	</c:if>
                
                <button class="cancel-button" onclick="history.back()">Back</button>
                <button name="registerButton" class="enter-button">Join us</button>
                <a class="login-redirect" href="${pageContext.request.contextPath}/login">Already a member?</a>
        	</form>
        </main>
    </body>
</html>