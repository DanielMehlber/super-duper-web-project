/*
Author: Maximilian Rublik
 */

"use strict"
function setAllEventListener() {
    document.getElementById("add-member-button").addEventListener("click", openModal);
    document.getElementsByClassName("add-member-modal-close")[0].addEventListener("click", closeModal);
    document.getElementsByName("remove-member-form")[0].addEventListener("submit", confirmDeleteMember);
    document.getElementsByName("remove-team-form")[0].addEventListener("submit", confirmDeleteTeam);
    document.getElementById("main").addEventListener("click", hideNav);
    document.getElementsByClassName("nav-bar-button")[0].addEventListener("mouseover", toggleNav);
}

function confirmDeleteTeam() {
    return confirm('Do you really want to delete this team?')
}

function confirmDeleteMember() {
    return confirm('Do you really want to delete this member from the team?')
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