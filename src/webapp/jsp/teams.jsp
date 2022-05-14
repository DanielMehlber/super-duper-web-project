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
            <h1 id="teams-title">Your Teams</h1>
            <div id="team-container">
                <img class="team-logo" src="data:image/jpeg;base64,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" />
                <h2 class="team-title">theTeam</h2>
                <p class="players">players</p>
                <p class="tags">#lol #league #theeldenring</p>
                <p class="players"> tim, tom, peter, paul</p>
            </div>

            <c:forEach items="${teamsBean.teams}" var="team">
                <img class="team-logo" src="${team.profilePicture}" />
                <h2 class="team-title">${team.name}</h2>
                <p class="tags">${team.tags}</p>
                <p class="player-count">${team.playercount}</p>
                ${team.name}
            </c:forEach>
        </main>
    </body>
</html>
