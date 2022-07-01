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

function deleteAlert(){
    window.alert("You have deleted the user");
}

function setAllEventListener(){
    document.getElementById("player-background-image").addEventListener("input", updateBackgroundImage)
    document.getElementById("profile-profile-image").addEventListener("input", updateProfileImage)
    document.getElementById("remove-user-button").addEventListener("click", deleteAlert)
}

//window.addEventListener("load", setAllEventListener)
document.addEventListener("DOMContentLoaded", setAllEventListener)