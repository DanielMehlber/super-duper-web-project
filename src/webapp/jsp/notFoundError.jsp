<%--
  Author: Philipp Phan
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.contextPath}/"/>
    <link href="stylesheets/Elements.css" rel="stylesheet">
    <link href="stylesheets/errorPage.css" rel="stylesheet">
</head>
<body>
    <div class="content-container">
        <p class="errorCode">404</p>
        <p>The page you were looking for was moved or doesn&#180;t exist :/</p>
        <p>Let&#180;s get you back</p>
        <a class="button" id="homeButton" href="dashboard">Home</a>
    </div>
</body>
</html>
