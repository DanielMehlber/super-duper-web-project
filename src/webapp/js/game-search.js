// Authors: Daniel Mehlber


/**
 * converts user JSON into HTML. Expected JSON:
 * 
 * @param game.id id of game
 * @param game.title title of game
 */
function generateGameCardHtml(game) {
    let html =
        `<div class="game-card">
    <img class="game-bg-image" src="games/images?id=${game.id}&type=background" alt="background"/>
    <div class="game-data">
        <div class="game-image">
            <img src="games/images?id=${game.id}&type=profile" alt="cover image"/>
        </div>
        <div class="game-title">
            ${game.title}
        </div>
    </div>
        <a href="games/game?id=${game.id}" class="view-game-button secondary-button button">View</a>
    </div>
    </div>`

    return html
}

function executeGameSearch() {
    const searchbar = document.getElementById("game-searchbar")
    const searchTerm = searchbar.value

    const playersList = document.getElementById("game-list")
    playersList.innerHTML = ""

    fetch("games/search?search="+searchTerm)
        .then(response => response.json())
        .then(gamesJson => {
            gamesJson.forEach(userJson => {
                const html = generateGameCardHtml(userJson)
                playersList.innerHTML += html
            })
        })
}

function setupAddGameButton() {
    let button = document.getElementById("add-game-button");
    button.addEventListener("click", openModal);
}

executeGameSearch()
setupAddGameButton()