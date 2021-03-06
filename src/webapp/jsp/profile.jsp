<%--
  Author: Philipp Phan
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.contextPath}/"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="stylesheets/Elements.css" rel="stylesheet">
    <link href="stylesheets/ProfilePage.css" rel="stylesheet">
    <link href="stylesheets/dashboard.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <script src="js/profile.js"></script>
    <script src="js/sidebar.js"></script>
</head>
<body>

<%@include file="fragments/sidebar.jspf" %>
<main id="main" class="flex-container">
    <button class="nav-bar-button">&#9776;</button>
    <section class="main-content-container">
        <img src="users/images?type=background&user=${requestScope.profileViewBean.user.username}"
             class="background-image" alt="Background Image of user"/>
        <c:if test="${requestScope.profileViewBean.editPermission}">
            <div class="background-image-container">
                <label class="bg-image-label">Edit Background Image: </label>
                <form class="background-image-upload"
                      id="player-background-image"
                      action="users/images?type=background&user=${requestScope.profileViewBean.user.username}"
                      method="POST"
                      enctype="multipart/form-data">
                    <label class="input-bg-label">
                        <input class="background-image-input" id="background-image-input" type="file" name="profile"
                               accept="image/*">
                        Upload Image
                    </label>
                </form>
            </div>
        </c:if>
        <div class="profile-card">
            <img src="users/images?type=profile&user=${requestScope.profileViewBean.user.username}"
                 class="main-profile-image" alt="Profile Image of user"/>
            <c:if test="${requestScope.profileViewBean.editPermission}">
                <label class="profile-image-label">Edit Profile Image:</label>
                <form class="profile-image-form" id="profile-profile-image" action="users/images"
                      method="POST" enctype="multipart/form-data">
                    <label class="input-profile-label">
                        Upload Image
                        <input class="profile-image-input" type="file" name="profile" accept="image/*">
                    </label>
                </form>
            </c:if>
            <div class="username">@${requestScope.profileViewBean.user.username}</div>
            <c:if test="${requestScope.profileViewBean.isAdmin && requestScope.profileViewBean.editPermission}">
                <p class="admin-status">You are an admin
                    <span role="tooltip">You can edit / remove games and remove users</span>
                </p>
            </c:if>
            <c:if test="${requestScope.profileViewBean.user.isAdmin}">
                <span class="platformRole">Admin</span>
            </c:if>
        </div>
        <c:if test="${requestScope.profileViewBean.isAdmin}">
            <form action="users/removeUser?username=${requestScope.profileViewBean.user.username}" method="POST" id="remove-user-form">
                <button class="remove-button">Delete User</button>
            </form>
        </c:if>
    </section>
</main>
<noscript><%@include file="fragments/javascript-deactivated.jspf" %></noscript>
</body>
</html>
