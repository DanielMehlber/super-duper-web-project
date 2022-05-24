<%--
  User: Maximilian Rublik
  Date: 23.05.2022
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>>
        <title>Add new team</title>
        <meta charset="UTF-8">
        <base href="${pageContext.request.requestURI}" />
        <link rel="stylesheet" href="../stylesheets/Elements.css">
        <link rel="stylesheet" href="../stylesheets/AddNewTeam.css">
    </head>
    <body>
        <main>
            <form class="add-team-card" action="${pageContext.request.contextPath}/teams/addnewteam" method="POST">
                <input name="name" class="name-input" type="text" placeholder="team name" required>
                <input name="slogan" class="slogan-input" type="text" placeholder="slogan">
                <input name="profile" class="profile-input" type="file" accept="/image"/>
                <input name="background" class="background-input" type="file" accept="/image"/>

                <button class="cancel-button">Cancel</button>
                <button class="enter-button">Save</button>
            </form>
        </main>
    </body>
</html>
