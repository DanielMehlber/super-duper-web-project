/*
Author: Maximilian Rublik
 */

"use strict";
function setAllEventListener(){
    document.getElementsByClassName("search-input")[0].addEventListener("keyup", executeTeamSearch)
    document.getElementById("add-team-button").addEventListener("click", openModal);
    document.getElementById("game-filter-selection").addEventListener("change", executeTeamSearch);
    document.getElementsByClassName("nav-bar-button")[0].addEventListener("mouseover", toggleNav);
    document.getElementById("main").addEventListener("click", hideNav);
    document.getElementsByClassName("team-creation-modal-close")[0].addEventListener("click", closeModal);
    document.getElementsByName("background")[0].addEventListener("change", checkBackground);
    document.getElementsByName("profile")[0].addEventListener("change", checkProfile);
}

/**
 * @param team.name name of team
 * @param team.id
 * @param team.slogan
 * @param team.tags
 * @param team.members list of team members
 * @param team.size
 */
function generateTeamCardHtml(team) {
    let html =
        `<a class="team-container" href="teams/team?id=${team.id}">
        <img alt="team logo" class="team-logo" src="teams/images?type=profile&id=${team.id}"
        <h2 class="team-title">${team.name}</h2>
        <p class="tags">${team.tags}</p>
        <div>`
            team.members.forEach(member => {
                html +=
                    `<img class="player-logo" src="users/images?type=profile&user=${member}" alt="player logo">`
            });
        html += `
        </div>
            <p class="member-count-paragraph">${team.size} members</p>
        </a>`

    return html;
}

function loadGames() {
    const gameSelection = document.getElementById("add-team-game-selection");
    const gameFilterSelection = document.getElementById("game-filter-selection");

    fetch("games/search?search=")
        .then(response => response.json())
        .then(gamesJson => {
            gamesJson.forEach(gameOption => {
                const html = generateGameOptionHtml(gameOption)
                gameSelection.innerHTML += html;
                gameFilterSelection.innerHTML += html;
            });
        });
}

/**
 * converts
 *
 * @param game.id id of the game
 * @param game.title title of the game
 */
function generateGameOptionHtml(game) {
    let html =
        `<option value="` + game.id + `">` + game.title + `</option>`

    return html;
}

function executeTeamSearch() {
    const searchbar = document.getElementsByClassName("search-input")[0];
    const gameFilterSelection = document.getElementById("game-filter-selection");
    const selectedGameFilter = gameFilterSelection.selectedIndex;
    const searchValue = searchbar.value;

    const teamList = document.getElementById("team-list");
    teamList.innerHTML = "";

    fetch("teams/search?filter=" + selectedGameFilter + "&un="+searchValue)
        .then(response => response.json())
        .then(teamJson => {
            teamJson.forEach(teamJson => {
                let html = generateTeamCardHtml(teamJson)
                teamList.innerHTML += html
            })
        });
}

function openModal() {
    let modal = document.getElementById("team-creation-modal");
    modal.classList.remove("invisible");
}

function closeModal() {
    let modal = document.getElementById("team-creation-modal");
    modal.classList.add("invisible");
}

document.addEventListener("DOMContentLoaded", setAllEventListener)

executeTeamSearch();
loadGames();
closeModal();