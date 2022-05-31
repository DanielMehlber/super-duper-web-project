<%--
  Author: Daniel Mehlber
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
    <%@include file="fragments/sidebar.jspf" %>

    <main id="main" onclick="hideNav()">
        <button class="nav-bar-button" onmouseover="toggleNav()">☰</button>
        <div class="dashboard-header">
            <h1>Welcome back</h1>
        </div>
    </main>

    <footer></footer>
</body>
</html>