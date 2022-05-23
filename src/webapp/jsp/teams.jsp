<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Maximilian Rublik
  Date: 14.05.2022
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <meta charset="UTF-8">
        <base href="${pageContext.request.requestURI}" />
        <link rel="stylesheet" href="../stylesheets/Elements.css">
        <link rel="stylesheet" href="../stylesheets/TeamsPage.css">
    </head>
    <body>
        <main>
        	<div class="head-information">
        		<h1 id="teams-title">Your Teams</h1>
        		<select class="game-selection">
					<option value="LeagueOfLegends">League of Legends</option>
				  	<option value="Counterstrike">Counterstrike</option>
				</select>
        		<input class="search-input" placeholder="Search" />
        		<a href="${pageContext.request.contextPath}/teams/addnewteam" class="add-button">+</a>
        	</div>
            <c:forEach items="${teamsBean.teams}" var="team">
				<a class="team-container" href="${pageContext.request.contextPath}/teams/team?id=${team.getKey().id}">
					<img class="team-logo" src="${pageContext.request.contextPath}/teams/images?type=profile&id=${team.getKey().id}" />
					<h2 class="team-title">${team.getKey().name}</h2>
					<p class="tags">${team.getKey().tags}</p>
				</a>
            </c:forEach>
        </main>
    </body>
</html>
