// Authors: Daniel Mehlber
"use strict";

document.addEventListener("DOMContentLoaded", initNav)

function showNav() {
    document.getElementById("main").classList.add("showing-nav");
    // document.getElementById("main").style.left = '200px';
}

function hideNav() {
    document.getElementById("main").classList.remove("showing-nav");
    // document.getElementById("main").style.left = '0px';
}

function toggleNav() {
    const nav = document.getElementById("main")
    if(nav.classList.contains("showing-nav")) {
        hideNav()
    } else {
        showNav()
    }
}

function logoutAlert(){
    window.alert("You have been logged out! See you soon.");
}

function initNav() {
    const navButtons = document.getElementsByClassName("nav-bar-button");
    if(navButtons[0] !== undefined) {
        navButtons[0].addEventListener("mouseover", toggleNav)
        navButtons[0].addEventListener("click", toggleNav)
    }

    hideNav()

    const main = document.getElementById("main");
    if(main !== undefined) {
        main.addEventListener("click", hideNav)
    }
    document.getElementById("sidebarLogOut").addEventListener("click", logoutAlert);
}