<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage=""%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration</title>
        <link rel="stylesheet" href="../stylesheets/Elements.css">
        <link rel="stylesheet" href="../stylesheets/RegistrationPage.css">
        <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    </head>
    <body>
        <main>
        	<form class="registration-card" method="POST" action="../registration">
                <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo" class="logo img">
                <input type="email" name="email" class="email" placeholder="Choose E-Mail" maxlength="100">
                <input type="text" name="username" class="username" placeholder="Choose Username" maxlength="100">
                <input type="password" name="password" class="password" minlength="8" placeholder="Choose Password">
                
                <button class="cancel-button" onclick="history.back()">Back</button>
                <button class="enter-button">Join us</button>
        	</form>
        </main>
    </body>
</html>