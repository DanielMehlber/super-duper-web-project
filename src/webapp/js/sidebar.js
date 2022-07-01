// Authors: Daniel Mehlber
"use strict";

document.addEventListener("DOMContentLoaded", initNav)

function showNav() {
    document.getElementById("main").style.left = '200px';
}

function hideNav() {
    document.getElementById("main").style.left = '0px';
}

function toggleNav() {
    const nav = document.getElementById("main")
    if(nav.style.left !== '0px') {
        hideNav()
    } else {
        showNav()
    }
}

function initNav() {
    document.getElementById("main").style.left = '0px';
    const navButtons = document.getElementsByClassName("nav-bar-button");
    if(navButtons[0] !== undefined) {
        navButtons[0].addEventListener("mouseover", toggleNav)
        navButtons[0].addEventListener("click", toggleNav)
    }

    const main = document.getElementById("main");
    if(main !== undefined) {
        main.addEventListener("click", hideNav)
    }
}