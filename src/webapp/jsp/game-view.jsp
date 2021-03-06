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
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="stylesheets/Elements.css"/>
    <link rel="stylesheet" href="stylesheets/dashboard.css"/>
    <link rel="stylesheet" href="stylesheets/game.css"/>
    <link rel="stylesheet" href="stylesheets/TeamsPage.css"/>
    <link rel="stylesheet" href="stylesheets/game-error-modal.css"/>
    <title>${requestScope.gamePageViewBean.game.name}</title>
</head>
<body>

<c:if test="${not empty requestScope.gamePageViewBean.error}">
    <%@include file="fragments/game-error-modal.jspf" %>
</c:if>

<script src="js/newsfeed.js"></script>
<script src="js/game-error-modal.js"></script>
<%@include file="fragments/sidebar.jspf" %>
    <main id="main">
        <button class="nav-bar-button">&#9776;</button>
        <c:if test="${requestScope.user.isAdmin}">
            <a href="games/game?id=${requestScope.gamePageViewBean.game.id}&mode=edit" class="game-edit-button button">&#9999;</a>
        </c:if>
        <div class="game-background-container">
            <img alt="game background image" class="game-background-image" src="games/images?id=${gamePageViewBean.game.id}&type=background">
        </div>
        <div class="game-main-container">
            <div class="game-information-container">
                <div class="game-cover-container">
                    <img class="game-cover-image" src="games/images?id=${gamePageViewBean.game.id}&type=profile" alt="game cover image"/>
                </div>
                <div class="game-title-container">
                    <h1>${requestScope.gamePageViewBean.game.name}</h1>
                </div>
                <div class="game-description-container">
                    ${requestScope.gamePageViewBean.game.description}
                </div>
            </div>
            <div class="game-teams-container">
                <h2>${requestScope.gamePageViewBean.teamsCount} Teams are playing ${requestScope.gamePageViewBean.game.name}</h2>
                <c:forEach items="${requestScope.gamePageViewBean.teams}" var="team">
                    <a class="team-container" href="${pageContext.request.contextPath}/teams/team?id=${team.id}">
                        <img class="team-background" src="${pageContext.request.contextPath}/teams/images?type=background&id=${team.id}" alt="team background image">
                        <img class="team-logo" src="${pageContext.request.contextPath}/teams/images?type=profile&id=${team.id}" alt="team logo"/>
                        <h2 class="team-title">${team.name}</h2>
                        <p class="tags">${team.tags}</p>
                        <div>
                            <c:forEach items="${team.members}" var="member">
                                <img class="player-logo" src="${pageContext.request.contextPath}/users/images?type=profile&user=${member.username}" alt="team logo">
                            </c:forEach>
                        </div>
                        <p class="member-count-paragraph">${team.memberCount} members</p>
                    </a>
                </c:forEach>
            </div>
            </div>
    </main>
    <noscript><%@include file="fragments/javascript-deactivated.jspf" %></noscript>
</body>
</html>