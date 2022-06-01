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
<div class="flex-container">
    <img src="${pageContext.request.contextPath}/users/images?type=background&user=${requestScope.profileViewBean.user.username}" class="background-image"/>
    <form class="background-image-upload" action="../users/image" method="post" enctype="multipart/form-data">
        <c:if test="${requestScope.profileViewBean.editPermission == true}">
            <input class="background-image-input" type="file" accept="image/*">
            <button class="accept-button-background" type="submit">Accept</button>
        </c:if>
    </form>

    <div class="profile-card">
        <!--
        <img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/edcb0e23689279.578bd6af7dffb.png" alt="Profile Banner" class="profile-banner"/>
        -->
        <div class="player-info">
            <form class="profile-image-upload" action="../users/image" method="post" enctype="multipart/form-data">
                <img src="${pageContext.request.contextPath}/users/images?type=profile&user=${requestScope.profileViewBean.user.username}" class="profile-image"/>
                <c:if test="${requestScope.profileViewBean.editPermission == true}">
                    <input class="profile-image-input" type="file" accept="image/*">
                    <button class="accept-button-profile" type="submit">Accept</button>
                </c:if>
            </form>
            <!--get Username user-->
            <p class="username">${requestScope.profileViewBean.user.username}</p>
            <!-- get E-Mail from user-->
            <p class="email">${requestScope.profileViewBean.user.email}</p>
        </div>

    </div>
</div>
</body>
</html>
