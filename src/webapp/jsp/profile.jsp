<%--
  Created by IntelliJ IDEA.
  User: Philipp Phan
  Date: 13.05.2022
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.requestURI}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../stylesheets/Elements.css">
    <link rel="stylesheet" href="../stylesheets/ProfilePage.css">
    <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
<%@include file="fragments/sidebar.jspf"%>
<main class="flex-container">
    <img src="${pageContext.request.contextPath}/users/images?type=background&user=${requestScope.profileViewBean.user.username}"
         class="background-image"/>
    <c:if test="${requestScope.profileViewBean.editPermission}">
        <div class="background-image-container">
            <label class="bg-image-label">Edit Background Image: </label>
            <form class="background-image-upload"
                  action="${pageContext.request.contextPath}/users/images?type=background&user=${requestScope.profileViewBean.user.username}"
                  method="POST"
                  enctype="multipart/form-data">
                <label class="input-bg-label">
                    <input class="background-image-input" type="file" name="profile" accept="/image/*">
                    Upload Image
                </label>
                <button class="accept-button-background" type="submit">Accept</button>
            </form>
        </div>
    </c:if>
    <div class="profile-card">
        <img src="${pageContext.request.contextPath}/users/images?type=profile&user=${requestScope.profileViewBean.user.username}"
             class="profile-image"/>
        <c:if test="${requestScope.profileViewBean.editPermission}">
            <label class="profile-image-label">Edit Profile Image:</label>
            <form class="profile-image-form" action="${pageContext.request.contextPath}/users/images"
                  method="POST" enctype="multipart/form-data">
                <label class="input-profile-label">
                    Upload Image
                    <input class="profile-image-input" type="file" name="profile" accept="image/*"/>
                </label>
                <button class="accept-button-profile" type="submit">Accept</button>
            </form>
        </c:if>
        <div class="username">@${requestScope.profileViewBean.user.username}</div>
        <!--Email noch in Nickname ändern-->
        <div class="nickname">${requestScope.profileViewBean.user.nickname}</div>
    </div>
    <c:if test="${requestScope.profileViewBean.editPermission}">
        <form class="log-out" action="../logout" method="Post">
            <button class="log-out-button" type="submit">logout</button>
        </form>
    </c:if>

    <c:if test="${requestScope.profileViewBean.isAdmin}">
        <a class="remove-button"
           href="${pageContext.request.contextPath}/users/removeUser?username=${requestScope.profileViewBean.user.username}">Remove
            User
        </a>
    </c:if>
</main>
</body>
</html>
