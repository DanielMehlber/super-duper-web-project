<jsp:useBean id="profileBean" scope="page" type="com.esports.manager.userManagement.entities.User"/>
<%--
  Created by IntelliJ IDEA.
  User: Philipp
  Date: 13.05.2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.requestURI}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--
    <link rel="stylesheet" href="../stylesheets/elements.css">
    <link rel="stylesheet" href="../stylesheets/profilePage.css">
    -->
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
<form class="profile-card" action="../profile" method="post">
    <p>Player profile</p>

    <img src="<c:/>" class="title-picture"/>

    <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Profile picture"
         class="profile_picture"/>
    <!--get Username user-->
    <p class="username">@${profileBean.username}</p>

    <!-- get E-Mail from user-->
    <p class="email">${profileBean.username}</p>

    <!-- get team name from user-->
    <p class="team">THeSports Red-White</p>

    <!-- get game from user-->
    <p class="game">League of Legends</p>

    <!--role-->
    <p class="game-role">Top-Laner</p>

    <!--add to team-->
    <button>Test</button>
</form>
</body>
</html>
