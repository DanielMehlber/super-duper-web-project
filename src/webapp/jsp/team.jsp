<%--
  User: Maximilian Rublik
--%>
<%@ page contentType="text/html;charset=UTF-8" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <base href="${pageContext.request.contextPath}/"/>
        <link href="stylesheets/Elements.css" rel="stylesheet"/>
        <link href="stylesheets/dashboard.css" rel="stylesheet"/>
        <link href="stylesheets/TeamPage.css" rel="stylesheet"/>
        <link href="stylesheets/add-member-modal.css" rel="stylesheet"/>
        <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
        <title>${teamViewBean.team.name}</title>
    </head>
    <body>
        <noscript>Javascript deactivated!</noscript>
        <script src="js/team.js" defer></script>
        <%@include file="fragments/sidebar.jspf" %>

        <main id="main">
            <button class="nav-bar-button">&#9776;</button>
            <div class="info-area">
                <div class="head-information">
                    <img class="team-logo" src="teams/images?type=profile&id=${teamViewBean.getTeam().getId()}" alt="teamLogo"/>
                    <h1 class="team-title">${teamViewBean.team.name}</h1>
                    <h3 class="team-slogan">${teamViewBean.team.slogan}</h3>

                    <c:if test="${teamViewBean.isTeamLeader}">
                        <form name="remove-team-form" method="POST" action="teams/removeTeam">
                            <button class="remove-team-button" type="submit">X</button>
                            <input type="hidden" value="${teamViewBean.team.id}" name="teamId">
                        </form>
                    </c:if>
                </div>

                <table>
                    <tr class="header-row">
                        <th>Player name</th>
                        <th>Since</th>
                        <th>Position</th>
                        <th><button id="add-member-button" class="primary-button">+</button>
                    </tr>
                    <c:forEach items="${teamViewBean.members}" var="member">
                        <tr>
                            <td>${member.username}</td>
                            <td>${member.since}</td>
                            <td>${member.role}</td>
                            <c:if test="${ not member.isTeamLeader}">
                                <td>
                                    <form name="remove-member-form" method="POST" action="teams/removeMember">
                                        <button class="remove-button" type="submit">-</button>
                                        <input type="hidden" value="${teamViewBean.getTeam().id}" name="id" />
                                        <input type="hidden" value="${member.username}" name="username" />
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <c:if test="${teamViewBean.hasGame}">
                <section class="game-area">
                    <h2>Played Game</h2>
                    <div class="game-container">
                        <img alt="game logo" class="game-cover-image" src="games/images?id=${teamViewBean.game.id}&type=profile">
                        <label class="game-title">${teamViewBean.game.name}</label>
                        <label class="game-description">${teamViewBean.game.description}</label>
                    </div>
                </section>
            </c:if>

            <section id="add-member-modal" class="add-member-modal-container">
                <form class="add-member-modal" action="teams/addmember" method="POST">
                    <h2 id="add-member-title">Add member</h2>
                    <select id="add-member-user-selection" class="user-selection" name="users" required>
                        <c:forEach items="${addMemberViewBean.users}" var="user">
                            <option value="${user.username}">${user.username}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="teamId" value="${addMemberViewBean.teamId}">
                    <input type="text" class="position-field" name="position" placeholder="position" autofocus="autofocus" maxlength="30"/>

                    <button id="add-selected-member-button" class="primary-button">Add</button>
                    <div class="add-member-modal-close">X</div>
                </form>
            </section>
        </main>
        <footer>
        </footer>
    </body>
</html>
