//Authors:
"use strict";

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