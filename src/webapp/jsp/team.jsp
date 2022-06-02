<%--
  User: Maximilian Rublik
  Date: 21.05.2022
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/stylesheets/TeamPage.css" rel="stylesheet"/>
        <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
        <title>${teamViewBean.team.name}</title>
    </head>
    <body>
        <nav>

        </nav>
        <main>
            <div class="info-area">
                <div class="head-information">
                    <img class="team-logo" src="${pageContext.request.contextPath}/teams/images?type=profile&id=${teamViewBean.getTeam().getId()}" alt="teamLogo"/>
                    <h1 class="team-title">${teamViewBean.team.name}</h1>
                </div>

                <table>
                    <tr class="header-row">
                        <th>Playername</th>
                        <th>Since</th>
                        <th>Position</th>
                        <th><a class="add-button" href="${pageContext.request.contextPath}/teams/addmember?teamid=${teamViewBean.getTeam().getId()}">+</a></th>
                    </tr>
                    <c:forEach items="${teamViewBean.members}" var="member">
                        <tr>
                            <td>${member.username}</td>
                            <td>${member.since}</td>
                            <td>${member.role}</td>
                            <td><a class="remove-button" href="${pageContext.request.contextPath}/teams/removemember?teamid=${teamViewBean.getTeam().getId()}&username=${member.username}">-</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </main>

        <footer>

        </footer>
    </body>
</html>
