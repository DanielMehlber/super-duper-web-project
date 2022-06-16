<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/Elements.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/dashboard.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/game.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/TeamsPage.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/game-edit.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheets/game-modal.css"/>
    <title>Games</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/game-edit.js"></script>
<main id="main">
    <a href="${pageContext.request.contextPath}/games/game?id=${requestScope.gamePageViewBean.game.id}&mode=view" class="game-edit-button button">&#128065;</a>
    <form id="game-background-container" method="post" class="game-background-container" action="${pageContext.request.contextPath}/games/images?id=${requestScope.gamePageViewBean.game.id}&type=background" enctype="multipart/form-data">
        <label class="game-background-selector-container secondary-button">
            Select background
            <input name="image" type="file" class="game-background-selector" oninput="updateBackgroundImage()" accept="image/*"/>
        </label>
        <img class="game-background-image" src="${pageContext.request.contextPath}/games/images?id=${requestScope.gamePageViewBean.game.id}&type=background" alt="background of game">
    </form>
    <div class="game-main-container">
        <div class="game-information-container">
            <form id="game-cover-container" class="game-cover-container" method="post" action="${pageContext.request.contextPath}/games/images?id=${requestScope.gamePageViewBean.game.id}&type=profile" enctype="multipart/form-data">
                <label class="game-cover-selector-container secondary-button">
                    Choose
                    <input type="file" name="image" accept="image/*" class="game-cover-selector" oninput="updateCoverImage()"/>
                </label>
                <img class="game-cover-image" src="${pageContext.request.contextPath}/games/images?id=${requestScope.gamePageViewBean.game.id}&type=profile" alt="cover of game"/>
            </form>
            <form method="post" class="game-title-container" action="${pageContext.request.contextPath}/games/game/edit?id=${requestScope.gamePageViewBean.game.id}&item=title">
                <input name="value" type="text" value="${requestScope.gamePageViewBean.game.name}"/>
                <button type="submit">&#128504;</button>
            </form>
            <form method="post" class="game-description-container" action="${pageContext.request.contextPath}/games/game/edit?id=${requestScope.gamePageViewBean.game.id}&item=description">
                <textarea name="value" class="game-description-editor">${requestScope.gamePageViewBean.game.description}</textarea>
                <button type="submit">&#128504;</button>
            </form>
        </div>
    </div>


</main>
</body>
</html>