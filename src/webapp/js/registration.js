//Authors:
"use strict";

function setAllEventListener() {
    document.getElementsByClassName("cancel-button")[0].addEventListener("click", history.back());
    document.getElementsByName("password")[0].addEventListener("keyup", checkPassword());
    document.getElementsByName("confirmPassword")[0].addEventListener("keyup", checkPassword());
}

function checkPassword () {
    let password = document.getElementsByName('password')[0];
    let confirmPassword = document.getElementsByName('confirmPassword')[0];
    let registerButton = document.getElementsByName('registerButton')[0];

    if (password.value ===
        confirmPassword.value) {
        registerButton.disabled = false;
        registerButton.className = 'enter-button';
        confirmPassword.setCustomValidity("");
    } else {
        registerButton.disabled = true;
        registerButton.className = 'disabled-enter-button';
        confirmPassword.setCustomValidity("Invalid Field.");
    }
}

document.addEventListener("DOMContentLoaced", setAllEventListener)