//Author: Maximilian Rublik
"use strict";

function setAllEventListener() {
    document.getElementsByName("profile")[0].addEventListener("change", checkProfile);
    document.getElementsByName("background")[0].addEventListener("change", checkBackground);
    document.getElementsByClassName("team-creation-modal-close")[0].addEventListener("click", closeModal);
}

function checkImageSize (input) {
    const saveButton = document.getElementsByName("enter-button")[0];
    const allowedSize = 10 * 1024 * 1024;
    if (input.files[0].size > allowedSize) {
        saveButton.disabled = true;
        saveButton.className = 'disabled-enter-button';
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

setAllEventListener();