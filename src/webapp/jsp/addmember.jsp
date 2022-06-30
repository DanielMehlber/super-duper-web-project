<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Maximilian Rublik
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add member</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.request.requestURI}" />
    <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/stylesheets/AddMember.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet"/>
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <noscript>JavaScript is deactivated!</noscript>
</head>
<body>
<%@include file="fragments/sidebar.jspf" %>
    <main>
        <button class="nav-bar-button" >&#9776;</button>
        <form class="add-member-form" action="${pageContext.request.contextPath}/teams/addmember" method="POST">
            <select class="user-selection" name="users">
                <c:forEach items="${addMemberViewBean.users}" var="user">
                    <option value="${user.username}">${user.username}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="teamId" value="${addMemberViewBean.teamId}">
            <input class="position-field" name="position" placeholder="position" autofocus="autofocus"/>

            <button class="enter-button" type="submit">Add</button>
        </form>
    </main>

    <footer>

    </footer>
</body>
</html>
