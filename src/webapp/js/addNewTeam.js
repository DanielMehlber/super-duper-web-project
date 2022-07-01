//Author: Maximilian Rubli
"use strict";

function setAllEventListener() {
    document.getElementsByName("profile")[0].addEventListener("change", checkProfile);
    document.getElementsByName("background")[0].addEventListener("change", checkBackground);
    document.getElementsByClassName("team-creation-modal-close")[0].addEventListener("click", closeModal);
}

function checkImageSize (input) {
    let saveButton = document.getElementsByName("enter-button")[0];
    const allowedSize = 1024 * 1024 * 10;
    if (input.files[0].size > allowedSize) {
        saveButton.className = 'disabled-enter-button';
        saveButton.disabled = true;
        input.setCustomValidity("Invalid Field.");
    } else {
        saveButton.disabled = false;
        saveButton.className = 'enter-button';
        input.setCustomValidity("");
    }
}

function checkProfile() {
    let input = document.getElementsByName("profile")[0];
    checkImageSize(input);
}

function checkBackground() {
    let input = document.getElementsByName("background")[0];
    checkImageSize(input);
}

document.addEventListener("DOMContentLoaded", setAllEventListener)
