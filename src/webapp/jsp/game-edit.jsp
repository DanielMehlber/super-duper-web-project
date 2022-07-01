<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="stylesheets/Elements.css"/>
    <link rel="stylesheet" href="stylesheets/dashboard.css"/>
    <link rel="stylesheet" href="stylesheets/game.css"/>
    <link rel="stylesheet" href="stylesheets/game-edit.css"/>
    <link rel="stylesheet" href="stylesheets/game-modal.css"/>
    <title>${requestScope.gamePageViewBean.game.name} | Edit</title>
</head>
<body>
    <script src="js/game-edit.js"></script>
    <script src="js/newsfeed.js"></script>
    <%@include file="fragments/sidebar.jspf" %>

    <main id="main">
        <button class="nav-bar-button">&#9776;</button>
        <div class="game-edit-buttons-container">
            <a href="games/game?id=${requestScope.gamePageViewBean.game.id}&mode=view" class="game-view-button button">&#128065;</a>
            <form method="post" action="games/game/edit">
                <button class="game-view-button game-delete-button primary-button" type="submit">&#128465;&#65039;</button>
                <input type="hidden" value="${requestScope.gamePageViewBean.game.id}" name="id"/>
                <input type="hidden" value="delete" name="mode"/>
            </form>
        </div>
        <form id="game-background-container" method="post" class="game-background-container" action="games/images?id=${requestScope.gamePageViewBean.game.id}&type=background" enctype="multipart/form-data">
            <label class="game-background-selector-container secondary-button">
                Select background
                <input name="image" type="file" class="game-background-selector" accept="image/*"/>
            </label>
            <img class="game-background-image" src="games/images?id=${requestScope.gamePageViewBean.game.id}&type=background" alt="background of game">
        </form>
        <div class="game-main-container">
            <div class="game-information-container">
                <form id="game-cover-container" class="game-cover-container" method="post" action="games/images?id=${requestScope.gamePageViewBean.game.id}&type=profile" enctype="multipart/form-data">
                    <label class="game-cover-selector-container secondary-button">
                        Choose
                        <input type="file" name="image" accept="image/*" class="game-cover-selector"/>
                    </label>
                    <img class="game-cover-image" src="games/images?id=${requestScope.gamePageViewBean.game.id}&type=profile" alt="cover of game"/>
                </form>
                <form method="post" class="game-title-container" accept-charset="utf-8" action="games/game/edit?id=${requestScope.gamePageViewBean.game.id}&item=title">
                    <input name="value" type="text" value="${requestScope.gamePageViewBean.game.name}"/>
                    <button type="submit">&#128504;</button>
                    <input type="hidden" value="edit" name="mode"/>
                </form>
                <form method="post" class="game-description-container" accept-charset="utf-8" action="games/game/edit?id=${requestScope.gamePageViewBean.game.id}&item=description">
                    <textarea name="value" class="game-description-editor">${requestScope.gamePageViewBean.game.description}</textarea>
                    <button type="submit">&#128504;</button>
                    <input type="hidden" value="edit" name="mode"/>
                </form>
            </div>
        </div>
    </main>
    <noscript><%@include file="fragments/javascript-deactivated.jspf" %></noscript>
</body>
</html>