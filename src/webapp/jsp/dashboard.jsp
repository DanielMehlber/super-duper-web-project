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
    <base href="${pageContext.request.contextPath}/"/>
    <title>Dashboard</title>
    <link href="stylesheets/Elements.css" rel="stylesheet"/>
    <link href="stylesheets/dashboard.css" rel="stylesheet"/>
    <link href="stylesheets/newsfeed.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body onload="loadMoreNewsfeedItems();">
    <script src="js/newsfeed.js"></script>

    <%@include file="fragments/sidebar.jspf" %>

    <main id="main">
        <button class="nav-bar-button">&#9776;</button>
        <section class="main-content-container">
            <section class="dashboard-content-container">
                <div class="dashboard-header">
                    <h1>Welcome back</h1>
                </div>
                <section class="newsfeed-container">
                    <h2>Newsfeed</h2>
                    <div id="newsfeed" class="newsfeed">
                    </div>
                </section>
                <%@include file="fragments/game-recommendation.jsp"%>
            </section>
        </section>
    </main>

    <footer></footer>
</body>
</html>