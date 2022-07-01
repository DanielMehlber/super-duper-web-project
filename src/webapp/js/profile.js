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

function deleteAlert(event){
    const result = confirm("Are you sure you want to remove the user");
    if(!result){
        event.preventDefault();
    }
}

function setAllEventListener(){
    document.getElementById("player-background-image").addEventListener("input", updateBackgroundImage)
    document.getElementById("profile-profile-image").addEventListener("input", updateProfileImage)
    document.getElementById("remove-user-form").addEventListener("submit", deleteAlert)
}

//window.addEventListener("load", setAllEventListener)
document.addEventListener("DOMContentLoaded", setAllEventListener)