// Authors: Philipp Phan
"use strict";
function updateBackgroundImage() {
    const form = document.getElementById("player-background-image");
    form.submit()
}
function updateProfileImage() {
    const form = document.getElementById("profile-profile-image");
    form.submit()
}
function setAllEventListener(){
    document.getElementById("player-background-image").addEventListener("input", updateBackgroundImage)
    document.getElementById("profile-profile-image").addEventListener("input", updateProfileImage)
}

window.addEventListener("load", setAllEventListener)
