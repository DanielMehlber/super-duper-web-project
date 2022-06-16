<%--
  User: Maximilian Rublik
  Date: 23.05.2022
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add new team</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <base href="${pageContext.request.requestURI}" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/Elements.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/AddNewTeam.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/dashboard.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/addNewTeam.js" />
    </head>
    <body>
    <%@include file="fragments/sidebar.jspf" %>
        <main id="main" onclick="hideNav()">
            <button class="nav-bar-button" onmouseover="toggleNav()">&#9776;</button>
            <section class="form-area">
                <h1>Add Team</h1>
                <form class="add-team-card" action="${pageContext.request.contextPath}/teams/addnewteam" method="POST" enctype="multipart/form-data">
                    <input name="name" class="name-input" type="text" placeholder="team name" required autofocus="autofocus"/>
                    <input name="slogan" class="slogan-input" type="text" placeholder="slogan"/>
                    <input name="profile" class="profile-input" onchange="checkProfile()" type="file" accept="image/*"/>
                    <input name="background" class="background-input" onchange="checkBackground()" type="file" accept="image/*"/>

                    <button class="cancel-button" onclick="history.back()">Cancel</button>
                    <button class="enter-button" type="submit" name="enter-button" >Save</button>
                </form>
            </section>
        </main>
    </body>
</html>
