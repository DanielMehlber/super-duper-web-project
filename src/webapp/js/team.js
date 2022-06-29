/*
Author: Maximilian Rublik
 */

"use strict"
function setAllEventListener() {
    document.getElementById("add-member-button").addEventListener("click", openModal);
}

function openModal() {
    let modal = document.getElementById("add-member-modal");
    modal.classList.remove("invisible")
}

function closeModal() {
    let modal = document.getElementById("add-member-modal");
    modal.classList.add("invisible")
}

document.addEventListener("DOMContentLoaded", setAllEventListener);
closeModal();