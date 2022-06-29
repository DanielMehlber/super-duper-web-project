// Authors: Daniel Mehlber


function updateBackgroundImage() {
    const form = document.getElementById("game-background-container");
    form.submit()
}

function updateCoverImage() {
    const form = document.getElementById("game-cover-container");
    form.submit()
}

function confirmDeletion() {
    return confirm('Do you really want to delete this game?')
}

function init() {
    document.getElementById("game-delete-button").addEventListener("submit", confirmDeletion)
    document.getElementById("game-background-image-selector").addEventListener("input", updateBackgroundImage)
    document.getElementById("game-cover-image-selector").addEventListener("input", updateCoverImage)
}

document.addEventListener("DOMContentLoaded", init)