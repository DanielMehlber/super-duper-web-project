//@Author Philipp Phan
"use strict";

window.addEventListener("DOMContentLoaded", checkCookies)

const body = document.getElementById("cookieText");

function checkCookies() {
    if (navigator.cookieEnabled === false) {
        //window.alert("Your Cookies are deactivated. It would be very kind of you to activate the Cookies")
        body.innerHTML = "Your Cookies are deactivated. You have to activate your Cookies";
    }
}
