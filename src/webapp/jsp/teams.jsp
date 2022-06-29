<%--
  User: Maximilian Rublik
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Title</title>
	<base href="${pageContext.request.requestURI}" />
	<link rel="stylesheet" href="../stylesheets/Elements.css">
	<link rel="stylesheet" href="../stylesheets/TeamsPage.css">
	<link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet"/>
	<link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
	<noscript>Javascript deactivated!</noscript>
	<%@include file="fragments/sidebar.jspf" %>
	<main id="main">
		<button class="nav-bar-button" >&#9776;</button>

		<section class="teams-area">
			<div class="head-information">
				<h1 id="teams-title">Your Teams</h1>
				<select class="game-selection">
					<option value="LeagueOfLegends">League of Legends</option>
					<option value="Counterstrike">Counterstrike</option>
				</select>
				<input type="text" class="search-input" placeholder="Search" />
				<a href="${pageContext.request.contextPath}/teams/addnewteam" class="add-button">+</a>
			</div>

			<c:forEach items="${teamsBean.teams}" var="team">
				<a class="team-container" href="${pageContext.request.contextPath}/teams/team?id=${team.getKey().id}">
					<img class="team-logo" src="${pageContext.request.contextPath}/teams/images?type=profile&id=${team.getKey().id}" alt="Team logo" />
					<h2 class="team-title">${team.getKey().name}</h2>
					<p class="tags">${team.getKey().tags}</p>
					<div>
						<c:forEach items="${team.value}" var="member">
							<img class="player-logo" src="${pageContext.request.contextPath}/users/images?type=profile&user=${member.username}" alt="player logo">
						</c:forEach>
					</div>
					<p class="member-count-paragraph">${team.value.size()} members</p>
				</a>
			</c:forEach>
		</section>
	</main>

	<footer></footer>
</body>
</html>
