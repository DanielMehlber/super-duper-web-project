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

        <main id="main" onclick="hideNav()">
            <button class="nav-bar-button" onmouseover="toggleNav()">&#9776;</button>
            <div class="info-area">
                <div class="head-information">
                    <img class="team-logo" src="teams/images?type=profile&id=${teamViewBean.getTeam().getId()}" alt="teamLogo"/>
                    <h1 class="team-title">${teamViewBean.team.name}</h1>
                </div>

                <table>
                    <tr class="header-row">
                        <th>Playername</th>
                        <th>Since</th>
                        <th>Position</th>
                        <th><button id="add-member-button" class="primary-button">+</button>
                    </tr>
                    <c:forEach items="${teamViewBean.members}" var="member">
                        <tr>
                            <td>${member.username}</td>
                            <td>${member.since}</td>
                            <td>${member.role}</td>
                            <td><a class="remove-button" href="teams/removemember?teamid=${teamViewBean.getTeam().getId()}&username=${member.username}">-</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <section id="add-member-modal" class="add-member-modal-container">
                <form class="add-member-modal" action="teams/addmember" method="POST">
                    <h2 id="add-member-title">Add member</h2>
                    <select id="add-member-user-selection" class="user-selection" name="users" required>
                        <c:forEach items="${addMemberViewBean.users}" var="user">
                            <option value="${user.username}">${user.username}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="teamId" value="${addMemberViewBean.teamId}">
                    <input class="position-field" name="position" placeholder="position" autofocus="autofocus"/>

                    <button id="add-selected-member-button" class="primary-button">Add</button>
                    <div class="add-member-modal-close" onclick="closeModal()">X</div>
                </form>
            </section>
        </main>
        <footer>
        </footer>
    </body>
</html>
