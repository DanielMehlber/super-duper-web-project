<!--@author Philipp Phan-->
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <title>Impressum</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.requestURI}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/Impressum.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <script src="${pageContext.request.contextPath}/js/removeUserConfirmation.js" defer></script>
</head>
<body>
<%@include file="fragments/sidebar.jspf" %>
<main id="main" onclick="hideNav()">
    <button class="nav-bar-button" onmouseover="toggleNav()">&#9776;</button>
    <section id="main-container">
        <div id="main-content">
            <h2>Impressum</h2>
            <p class="subheading">Angaben gem&auml;&szlig; &sect; 5 TMG</p>
            <p>
                ZeroFourNine AG
                <br>
                Esplanade 10
                <br>
                85049 Ingolstadt
                <br>
            </p>
            <div>
                <p class="subheading">Vertreten durch:</p>
                <br>
                Philian Phipp
                <br>
                Maniel Dehlber
                <br>
                Raximilian Mublik
                <br>
                <br>
            </div>
            <div>
                <p class="subheading">Kontakt:</p>
                <br>
                Telefon: <a href="https://goo.gl/maps/R1CF8m6Wf8kJUZtg9">089 24290495</a>
                <br>
                E-Mail: <a href='hop4600@thi.de'>hop4600@thi.de</a>
                <br>
                <br>
            </div>
            <div>
                <p class="subheading">Registereintrag:</p>
                <br>
                Eintragung im Handelsregister.
                <br>
                Registergericht: M&uuml;nchen
                <br>
                Registernummer: 1337
                <br>
                <br>
            </div>
            <noscript>Your Browser does not support JavaScript</noscript>
        </div>
    </section>
    <noscript><%@include file="fragments/javascript-deactivated.jspf" %></noscript>
</main>
</body>
</html>
