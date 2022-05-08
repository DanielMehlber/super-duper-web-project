<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage=""%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration</title>
        <link rel="stylesheet" href="../ui/elements.css">
        <link rel="stylesheet" href="RegistrationPage.css">
        <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    </head>
    <body>
        <main>
            <div class="registration-card">
                <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/atlassian_jira_logo_icon_170511.png" alt="Logo" class="logo img">
                <input type="email" class="email" placeholder="Choose E-Mail" maxlength="100">
                <input type="text" class="username text" placeholder="Choose Username" maxlength="100">
                <input type="password" class="password" placeholder="Choose Password">
                <button class="cancel-button" onclick="history.back()">Back</button>
                <button class="enter-button">Join us</button>
            </div>
        </main>
    </body>
</html>