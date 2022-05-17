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
    <div class="empty1"></div>


    <form class="profile-card" action="../profile" method="post">
        <!--
        <img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/edcb0e23689279.578bd6af7dffb.png" alt="Profile Banner" class="profile-banner"/>
        -->
        <div class="player-info">
            <img src="../img/profile_image.jpeg"
                 class="profile-picture" ${requestScope.profileBean.profile_picture}/>
            <!--get Username user-->
            <p class="username">@wandolf${requestScope.profileBean.username}</p>
            <!-- get E-Mail from user-->
            <p class="email">manield@pwc.com${requestScope.profileBean.email}</p>
            <p class="user_level">20</p>
        </div>

        <!-- <p class="title">Active Team</p>-->
        <div class="team-container">
            <!-- get team name from user-->
            <p class="team">THeSports</p>
            <!--team-image-->
            <img src="../img/technische_hochschule_ingolstadt_59242c91ab8b7.png"
                 alt="team-picture"
                 class="team-image"/>
            <p class="members">32 Members${team.members}</p>
            <!-- get game from user-->
            <p class="member-images">member-images</p>
            <!--role-->
            <p class="team-tags">#CSGO #Competetive #NeverStopWinning #Money #B1tchez #MatzeF1ck3n${team.tags}</p>
        </div>

        <div class="activity-container">

        </div>

        <!--add to team-->
        <!--<button>Test</button>-->
    </form>

    <div class="empty2"></div>
</div>
</body>
</html>
