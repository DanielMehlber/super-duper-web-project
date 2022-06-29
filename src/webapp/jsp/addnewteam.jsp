<%--
  User: Maximilian Rublik
  Date: 23.05.2022
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>>
        <title>Add new team</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <base href="${pageContext.request.requestURI}" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/Elements.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/AddNewTeam.css">
        <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet"/>
    </head>
    <body>
    <%@include file="fragments/sidebar.jspf" %>
        <main>
            <button class="nav-bar-button" onmouseover="toggleNav()">&#9776;</button>
            <form class="add-team-card" action="${pageContext.request.contextPath}/teams/addnewteam" method="POST" enctype="multipart/form-data">
                <input name="name" class="name-input" type="text" placeholder="team name" required autofocus="autofocus">
                <input name="slogan" class="slogan-input" type="text" placeholder="slogan">
                <input name="profile" class="profile-input" type="file" accept="/image"/>
                <input name="background" class="background-input" type="file" accept="/image"/>

                <button class="cancel-button">Cancel</button>
                <button class="enter-button">Save</button>
            </form>
        </main>
    </body>
</html>
