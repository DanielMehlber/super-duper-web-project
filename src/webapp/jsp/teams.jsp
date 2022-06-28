<%--
  User: Maximilian Rublik
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Title</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/Elements.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/TeamsPage.css">
	<link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet"/>
	<link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
	<noscript>Javascript deactivated!</noscript>
	<script src="${pageContext.request.contextPath}/js/teams.js" defer></script>
	<%@include file="fragments/sidebar.jspf" %>

	<main id="main" onclick="hideNav()">
		<button class="nav-bar-button" onmouseover="toggleNav()">&#9776;</button>
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

			<div id="team-list"></div>
		</section>
	</main>

	<footer></footer>
</body>
</html>
