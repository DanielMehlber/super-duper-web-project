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
            <c:forEach items="${teamsBean.teams}" var="team">
                <div onclick="" id="team-container">
                    <img class="team-logo" src="${pageContext.request.contextPath}/teams/images?type=profile&id=${team.id}" />
                    <h2 class="team-title">${team.name}</h2>
                    <p class="tags">${team.tags}</p>
                </div>
            </c:forEach>
        </main>
    </body>
</html>
