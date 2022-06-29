<%--
  Author: Philipp Phan
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.requestURI}"/>
    <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/errorPage.css" rel="stylesheet">
</head>
<body>
    <div class="content-container">
        <p id="errorCode404">404</p>
        <p>The page you were looking for was moved or doesn&#180;t exist :/</p>
        <p>Let&#180;s get you back</p>
        <a class="button" id="homeButton" href="${pageContext.request.contextPath}/dashboard">Home</a>
    </div>
</body>
</html>
