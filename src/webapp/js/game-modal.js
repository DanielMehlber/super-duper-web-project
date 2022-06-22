// Authors: Daniel Mehlber

function openModal() {
    const main = document.getElementById("main");
    const modal = document.getElementById("game-creation-modal");
    modal.classList.remove("invisible")
}

function closeModal() {
    const main = document.getElementById("main")
    const modal = document.getElementById("game-creation-modal")
    modal.classList.add("invisible")
}

closeModal()