// Authors: Daniel Mehlber

export function openModal() {
    const modal = document.getElementById("game-creation-modal");
    modal.classList.remove("invisible")
}

export function closeModal() {
    const modal = document.getElementById("game-creation-modal")
    modal.classList.add("invisible")
}

document.addEventListener("DOMContentLoaded", closeModal)