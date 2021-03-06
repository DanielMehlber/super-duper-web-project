<%--
  Author: Maximilian Rublik
  Date: 10.05.2022
--%>
<%@ page language="java" pageEncoding="UTF-8" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Registration</title>
        <meta charset="UTF-8">
        <base href="${pageContext.request.contextPath}/"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="stylesheets/Elements.css">
        <link rel="stylesheet" href="stylesheets/RegistrationPage.css">
        <script src="js/registration.js"></script>

        <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    </head>
    <body>
        <main>
        	<form class="registration-card" method="POST" action="registration">
                <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo" class="logo img">
                <input type="email" name="email" class="email" placeholder="Choose E-Mail" maxlength="40" required autofocus>
                <input type="text" name="username" class="username" placeholder="Choose Username" maxlength="30"
                       title="Only use letters or numbers for the username" pattern="^[a-zA-Z_\d]*$" required>
                <input type="password" name="password" class="password" minlength="8" placeholder="Choose Password"
                       title="Minimum 8 letters required" required>
                <input type="password" name="confirmPassword" class="confirmPassword" minlength="8" placeholder="Confirm Password" required>
                
                <c:if test="${not empty requestScope.registrationBean.errorMessage}">
                	<div class="error-message-text">${requestScope.registrationBean.errorMessage}</div>
               	</c:if>
                
                <button class="cancel-button" type="reset">Reset</button>
                <button name="registerButton" class="enter-button" type="submit">Join us</button>
                <a class="login-redirect" href="login">Already a member?</a>
        	</form>
        </main>
        <noscript><%@include file="fragments/javascript-deactivated.jspf" %></noscript>
    </body>
</html>