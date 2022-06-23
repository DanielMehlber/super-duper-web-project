<!--@author Philipp Phan-->
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Impressum</title>
    <meta charset="UTF-8">
    <base href="${pageContext.request.requestURI}"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    ${pageContext.request.contextPath}/stylesheets/Impressum.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/Elements.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/dashboard.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/stylesheets/Impressum.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Inter' rel='stylesheet'>
    <script src="${pageContext.request.contextPath}/js/removeUserConfirmation.js" defer></script>
</head>
<body>
<%@include file="fragments/sidebar.jspf" %>
<main id="main" onclick="hideNav()">
    <button class="nav-bar-button" onmouseover="toggleNav()">=</button>
    <section id="main-container">
        <div id="main-content">
            <h1>Impressum</h1>
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
            </div>
            <div>
                <p class="subheading">Verantwortlich f&uuml;r den Inhalt nach &sect; 55 Abs. 2 RStV:</p>
                <br>
                Maniel Dehlber
                <br>
                Esplanade 10
                <br>
                85049 Ingolstadt
                <br>
                <br>
            </div>
            <div>
                <p class="subheading">Haftungsausschluss:</p>
                <br>
                <p class="subheading">Haftung f&uuml;r Inhalte</p>
                <br>
                Die Inhalte unserer Seiten wurden mit gr&ouml;&szlig;ter Sorgfalt erstellt. F&uuml;r die Richtigkeit, Vollst&aumlndigkeit
                und Aktualit&aumlt der Inhalte k&ouml;nnen wir jedoch keine Gew&aumlhr &uuml;bernehmen. Als Diensteanbieter sind wir gem&auml&szlig;
                &sect; 7 Abs.1 TMG f&uuml;r eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich. Nach &sect;&sect;
                8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, &uuml;bermittelte oder gespeicherte
                fremde Informationen zu &uuml;berwachen oder nach Umst&aumlnden zu forschen, die auf eine rechtswidrige T&aumltigkeit
                hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung von Informationen nach den
                allgemeinen Gesetzen bleiben hiervon unber&uuml;hrt. Eine diesbez&uuml;gliche Haftung ist jedoch erst ab dem
                Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung m&ouml;glich. Bei Bekanntwerden von entsprechenden
                Rechtsverletzungen werden wir diese Inhalte umgehend entfernen.
                <br>
                <br>
            </div>
            <div>
                <p class="subheading">Datenschutz</p>
                <br>
                Die Nutzung unserer Webseite ist in der Regel ohne Angabe personenbezogener Daten m&ouml;glich. Soweit
                auf unseren Seiten personenbezogene Daten (beispielsweise Name, Anschrift oder eMail-Adressen)
                erhoben werden, erfolgt dies, soweit m&ouml;glich, stets auf freiwilliger Basis. Diese Daten werden ohne
                Ihre ausdr&uuml;ckliche Zustimmung nicht an Dritte weitergegeben.
                <br>
                Wir weisen darauf hin, dass die Daten&uuml;bertragung im Internet (z.B. bei der Kommunikation per E-Mail)
                Sicherheitsl&uuml;cken aufweisen kann. Ein l&uuml;ckenloser Schutz der Daten vor dem Zugriff durch Dritte ist
                nicht m&ouml;glich.
                <br>
                Der Nutzung von im Rahmen der Impressumspflicht ver&ouml;ffentlichten Kontaktdaten durch Dritte zur
                &uuml;bersendung von nicht ausdr&uuml;cklich angeforderter Werbung und Informationsmaterialien wird hiermit
                ausdr&uuml;cklich widersprochen. Die Betreiber der Seiten behalten sich ausdr&uuml;cklich rechtliche Schritte
                im Falle der unverlangten Zusendung von Werbeinformationen, etwa durch Spam-Mails, vor.
                <br>
            </div>
            <noscript>Your Browser does not support JavaScript</noscript>
        </div>
    </section>
</main>
</body>
</html>
