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
	<link href="${pageContext.request.contextPath}/stylesheets/team-creation-modal.css" rel="stylesheet"/>
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
				<button id="add-team-button" class="primary-button">+</button>
			</div>

			<div id="team-list"></div>
		</section>

		<section id="team-creation-modal" class="team-creation-modal-container">
			<form class="team-creation-modal" action="${pageContext.request.contextPath}/teams/addnewteam" method="POST" enctype="multipart/form-data">
				<h2 id="add-team-header">Add team</h2>
				<input name="name" class="team-name-input" type="text" placeholder="team name" required/>
				<input name="slogan" class="slogan-input" type="text" placeholder="slogan"/>

				<select name="selection" id="add-team-game-selection" class="add-team-modal-game-selection">
					<option selected value>none</option>
				</select>
				<textarea class="tags-textarea" placeholder="Insert tags comma ' , ' seperated"></textarea>

				<label class="team-logo-label">
					UPLOAD LOGO
					<input name="profile" class="team-logo-input" onchange="checkProfile()" type="file" accept="image/*"/>
				</label>

				<label class="background-label">
					BACKGROUND
					<input name="background" class="background-input" onchange="checkBackground()" type="file" accept="image/*"/>
				</label>

				<button class="enter-button" type="submit" name="enter-button">Save</button>
				<div class="team-creation-modal-close" onclick="closeModal()">X</div>
			</form>
		</section>
	</main>

	<footer></footer>
</body>
</html>
