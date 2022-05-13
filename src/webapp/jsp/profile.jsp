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
    <p>Player profile</p>

    <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Profile picture"
    class="profile_picture">
    <!--get Username user-->
    <label>@</label>

    <!-- get E-Mail from user-->
    <p></p>

    <!-- get team name from user-->
    <p>THeSports Red-White</p>

    <!-- get game from user-->
    <p>League of Legends</p>

    <!--role-->
    <p>Top-Laner</p>

    <!--add to team-->
    <button>Follow</button>

</body>
</html>
