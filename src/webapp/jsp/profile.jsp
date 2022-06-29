<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.requestURI}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/ProfilePage.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>

    <script src="${pageContext.request.contextPath}/js/removeUserConfirmation.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/profile.js"></script>
</head>
<body>

<%@include file="fragments/sidebar.jspf" %>
<main id="main" class="flex-container" onclick="hideNav()">
    <button class="nav-bar-button" onmouseover="toggleNav()">=</button>
    <section class="main-content-container">
        <img src="${pageContext.request.contextPath}/users/images?type=background&user=${requestScope.profileViewBean.user.username}"
             class="background-image" alt="Background Image of user"/>
        <c:if test="${requestScope.profileViewBean.editPermission}">
            <div class="background-image-container">
                <label class="bg-image-label">Edit Background Image: </label>
                <form class="background-image-upload"
                      id="player-background-image"
                      action="${pageContext.request.contextPath}/users/images?type=background&user=${requestScope.profileViewBean.user.username}"
                      method="POST"
                      enctype="multipart/form-data">
                    <label class="input-bg-label">
                        <input class="background-image-input" id="background-image-input" type="file" name="profile"  accept="image/*">
                        Upload Image
                    </label>
                </form>
            </div>
        </c:if>
        <div class="profile-card">
            <img src="${pageContext.request.contextPath}/users/images?type=profile&user=${requestScope.profileViewBean.user.username}"
                 class="main-profile-image" alt="Profile Image of user"/>
            <c:if test="${requestScope.profileViewBean.editPermission}">
                <label class="profile-image-label">Edit Profile Image:</label>
                <form class="profile-image-form" id="profile-profile-image" action="${pageContext.request.contextPath}/users/images"
                      method="POST" enctype="multipart/form-data">
                    <label class="input-profile-label">
                        Upload Image
                        <input class="profile-image-input" type="file" name="profile" oninput="updateProfileImage()" accept="image/*">
                    </label>
                </form>
            </c:if>
            <div class="username">@${requestScope.profileViewBean.user.username}</div>
            <!--Email noch in Nickname ändern-->
            <div class="nickname">${requestScope.profileViewBean.user.nickname}</div>
        </div>
        <c:if test="${requestScope.profileViewBean.isAdmin}">
            <a class="remove-button" id="remove-user-button"
               href="${pageContext.request.contextPath}/users/removeUser?username=${requestScope.profileViewBean.user.username}">Remove
                User
            </a>
        </c:if>
        <noscript>Your Browser does not support JavaScript</noscript>
    </section>
</main>
</body>
</html>
