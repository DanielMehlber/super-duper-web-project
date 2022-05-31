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
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <noscript>JavaScript is deactivated!</noscript>
</head>
<body>
    <nav>

    </nav>
    <main>
        <div class="main-container">
            <form action="${pageContext.request.contextPath}/teams/addmember" method="POST">
                <select name="users">
                    <c:forEach items="${addMemberViewBean.users}" var="user">
                        <option value="${user.username}">${user.username}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="teamId" value="${addMemberViewBean.teamId}">
                <input name="position" placeholder="position" autofocus="autofocus"/>

                <button class="cancel-button">Cancel</button>
                <button class="enter-button">Add</button>
            </form>
        </div>
    </main>

    <footer>

    </footer>
</body>
</html>
