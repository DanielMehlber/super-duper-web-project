<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/Elements.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/dashboard.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/game.css"/>

    <title>Games</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/newsfeed.js"></script>
<%@include file="fragments/sidebar.jspf" %>
<main id="main" onclick="hideNav()">
    <button class="nav-bar-button" onmouseover="toggleNav()">☰</button>
    <c:if test="${requestScope.user.isAdmin}">
        <a href="${pageContext.request.contextPath}/games/game?id=${requestScope.gamePageViewBean.game.id}&mode=edit" class="game-edit-button button">&#9999;</a>
    </c:if>
    <div class="game-background-container">
        <img class="game-background-image" src="${pageContext.request.contextPath}/games/images?id=${gamePageViewBean.game.id}&type=background">
    </div>
    <div class="game-main-container">
        <div class="game-information-container">
            <div class="game-cover-container">
                <img class="game-cover-image" src="${pageContext.request.contextPath}/games/images?id=${gamePageViewBean.game.id}&type=profile" alt=""/>
            </div>
            <div class="game-title-container">
                <h1>${requestScope.gamePageViewBean.game.name}</h1>
            </div>
            <div class="game-description-container">
                ${requestScope.gamePageViewBean.game.description}
            </div>
        </div>
        <!--
        <div class="game-teams-container">
            <h2>Teams</h2>
            <a class="team-container" href="${pageContext.request.contextPath}/teams/team?id=${team.getKey().id}">
                <img class="team-logo" src="${pageContext.request.contextPath}/teams/images?type=profile&id=${team.getKey().id}" />
                <h2 class="team-title">${team.getKey().name}</h2>
                <p class="tags">${team.getKey().tags}</p>
                <div>
                    <c:forEach items="${team.value}" var="member">
                        <img class="player-logo" src="${pageContext.request.contextPath}/users/images?type=profile&user=${member.username}" alt="player logo">
                    </c:forEach>
                </div>
                <p class="member-count-paragraph">${team.value.size()} members</p>
            </a>
        </div>
        -->
    </div>
</main>
</body>
</html>