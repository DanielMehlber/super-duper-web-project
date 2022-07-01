// Authors: Daniel Mehlber
"use strict";

function openModal() {
    const modal = document.getElementById("game-creation-modal");
    if(modal)
        modal.classList.remove("invisible")
}

function closeModal() {
    const modal = document.getElementById("game-creation-modal")
    if(modal)
        modal.classList.add("invisible")
}

document.addEventListener("DOMContentLoaded", closeModal)