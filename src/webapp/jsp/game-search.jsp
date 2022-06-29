<%--
  Author: Daniel Mehlber
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Players</title>
    <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/stylesheets/game-search.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/stylesheets/game-modal.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <script type="module" src="${pageContext.request.contextPath}/js/game-search.js" defer></script>
</head>
<body>

    <%@include file="fragments/sidebar.jspf" %>

    <main id="main">
        <button class="nav-bar-button" >&#9776;</button>
        <section class="games-area">
            <h1>Games Area</h1>
            <c:if test="${requestScope.user.isAdmin}">
                <button id="add-game-button" class="primary-button">+</button>
            </c:if>
            <input id="game-searchbar" type="text" placeholder="Search..."/>
            <div id="game-list"></div>
        </section>
        <c:if test="${requestScope.user.isAdmin}">
            <section id="game-creation-modal" class="game-creation-modal-container">
                <form class="game-creation-modal" action="${pageContext.request.contextPath}/games/new" method="post">
                    <h2>Create new Game</h2>
                    <input name="title" type="text" placeholder="Name of new Game" required/>
                    <button type="submit">Send</button>
                    <div class="game-creation-modal-close">X</div>
                </form>
            </section>
        </c:if>
    </main>
</body>
</html>