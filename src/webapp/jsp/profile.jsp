<%--
  Created by IntelliJ IDEA.
  User: Philipp Phan
  Date: 13.05.2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.requestURI}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../stylesheets/Elements.css">
    <link rel="stylesheet" href="../stylesheets/ProfilePage.css">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
<main class="flex-container">
    <div class="background-image-container">
        <img src="${pageContext.request.contextPath}/users/images?type=background&user=${requestScope.profileViewBean.user.username}"
             class="background-image"/>
        <form class="background-image-upload" action="${pageContext.request.contextPath}" method="POST"
              enctype="multipart/form-data">
            <c:if test="${requestScope.profileViewBean.editPermission}">
                <input class="background-image-input" type="file" name="profile" accept="/image/*">
                <button class="accept-button-background" type="submit">Accept</button>
            </c:if>
        </form>
    </div>
    <div class="profile-card">
        <div class="player-info">
            <img src="${pageContext.request.contextPath}/users/images?type=profile&user=${requestScope.profileViewBean.user.username}"
                 class="profile-image"/>
            <c:if test="${requestScope.profileViewBean.editPermission}">
                <form class="profile-image-upload" action="${pageContext.request.contextPath}/users/images"
                      method="POST" enctype="multipart/form-data">
                    <input class="profile-image-input" type="file" name="profile" accept="image/*"/>
                    <button class="accept-button-profile" type="submit">Accept</button>
                </form>
            </c:if>
            <div class="username">${requestScope.profileViewBean.user.username}</div>
            <div class="email">${requestScope.profileViewBean.user.email}</div>
        </div>
    </div>
</main>
</body>
</html>
