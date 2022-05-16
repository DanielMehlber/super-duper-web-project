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
    <script src="${pageContext.request.contextPath}/js/dashboard.js" defer></script>
    <nav>
        <div class="profile-container">
            <a class="profile-image" href="/">
                <img src="${pageContext.request.contextPath}/users/images?type=profile&user=${requestScope.dashboardBean.username}" width="100px" height="100px"/>
            </a>
            <div class="player-username">
                ${requestScope.dashboardBean.username}
            </div>
        </div>
        <ul class="navigation-options-container">
            <li class="selected nav-option">
                <a href="/dashboard">Home</a>
            </li>
            <li class="unselected nav-option">
                <a href="/clubs">Clubs</a>
            </li>
            <li class="unselected nav-option">
                <a href="/games">Games</a>
            </li>
            <li class="unselected nav-option">
                <a href="/settings">Settings</a>
            </li>
        </ul>
    </nav>

    <main id="main" onclick="hideNav()">
        <button class="nav-bar-button" onmouseover="toggleNav()">☰</button>
        <div class="dashboard-header">
            <h1>Welcome back</h1>
        </div>
    </main>

    <footer></footer>
</body>
</html>