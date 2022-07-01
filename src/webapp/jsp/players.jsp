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
    <title>Players</title>
    <link href="stylesheets/Elements.css" rel="stylesheet"/>
    <link href="stylesheets/member.css" rel="stylesheet"/>
    <link href="stylesheets/dashboard.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
</head>
<body>
    <script src="js/member.js" defer></script>

    <%@include file="fragments/sidebar.jspf" %>

    <main id="main">
        <button class="nav-bar-button" >&#9776;</button>
        <section class="players-area">
            <h1>Players Area</h1>
            <input id="player-searchbar" type="text" placeholder="Search..." />
            <div id="player-list"></div>
        </section>
    </main>

    <footer></footer>
</body>
</html>