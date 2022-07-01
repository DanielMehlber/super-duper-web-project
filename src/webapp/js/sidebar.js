// Authors: Daniel Mehlber
"use strict";

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

function initNav() {
    hideNav()
}

initNav()