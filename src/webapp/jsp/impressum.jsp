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
            <p class="subheading">Angaben gemäß &sect; 5 TMG</p>
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
                Philan Phipp
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
                Telefon: 49-089 24290495
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
                Registergericht: München
                <br>
                Registernummer: 1337
                <br>
            </div>
            <div>
                <p class="subheading">Verantwortlich für den Inhalt nach &sect; 55 Abs. 2 RStV:</p>
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
                <p class="subheading">Haftung für Inhalte</p>
                <br>
                Die Inhalte unserer Seiten wurden mit größter Sorgfalt erstellt. Für die Richtigkeit, Vollständigkeit
                und Aktualität der Inhalte können wir jedoch keine Gewähr übernehmen. Als Diensteanbieter sind wir gemäß
                &sect; 7 Abs.1 TMG für eigene Inhalte auf diesen Seiten nach den allgemeinen Gesetzen verantwortlich. Nach &sect;&sect;
                8 bis 10 TMG sind wir als Diensteanbieter jedoch nicht verpflichtet, übermittelte oder gespeicherte
                fremde Informationen zu überwachen oder nach Umständen zu forschen, die auf eine rechtswidrige Tätigkeit
                hinweisen. Verpflichtungen zur Entfernung oder Sperrung der Nutzung von Informationen nach den
                allgemeinen Gesetzen bleiben hiervon unberührt. Eine diesbezügliche Haftung ist jedoch erst ab dem
                Zeitpunkt der Kenntnis einer konkreten Rechtsverletzung möglich. Bei Bekanntwerden von entsprechenden
                Rechtsverletzungen werden wir diese Inhalte umgehend entfernen.
                <br>
                <br>
            </div>
            <div>
                <p class="subheading">Datenschutz</p>
                <br>
                Die Nutzung unserer Webseite ist in der Regel ohne Angabe personenbezogener Daten möglich. Soweit
                auf unseren Seiten personenbezogene Daten (beispielsweise Name, Anschrift oder eMail-Adressen)
                erhoben werden, erfolgt dies, soweit möglich, stets auf freiwilliger Basis. Diese Daten werden ohne
                Ihre ausdrückliche Zustimmung nicht an Dritte weitergegeben.
                <br>
                Wir weisen darauf hin, dass die Datenübertragung im Internet (z.B. bei der Kommunikation per E-Mail)
                Sicherheitslücken aufweisen kann. Ein lückenloser Schutz der Daten vor dem Zugriff durch Dritte ist
                nicht möglich.
                <br>
                Der Nutzung von im Rahmen der Impressumspflicht veröffentlichten Kontaktdaten durch Dritte zur
                Übersendung von nicht ausdrücklich angeforderter Werbung und Informationsmaterialien wird hiermit
                ausdrücklich widersprochen. Die Betreiber der Seiten behalten sich ausdrücklich rechtliche Schritte
                im Falle der unverlangten Zusendung von Werbeinformationen, etwa durch Spam-Mails, vor.
                <br>
            </div>
            <noscript>Your Browser does not support JavaScript</noscript>
        </div>
    </section>
</main>
</body>
</html>
