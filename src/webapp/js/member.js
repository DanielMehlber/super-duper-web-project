
/**
 * converts user JSON into HTML. Expected JSON:
 * 
 * @param user.username username of user
 * @param user.nickname nickname of user
 * @param user.teamIds list of team ids the user is member of
 */
function generateUserCardHtml(user) {
    let html =
        `<div class="member-card">
    <img class="member-bg-image" src="users/images?user=${user.username}&type=background" alt="background"/>
    <div class="member-data">
        <div class="profile-image">
            <img src="users/images?user=${user.username}&type=profile" alt="username"/>
        </div>
        <div class="member-handle">
            @<span class="member-username">${user.username}</span>
        </div>
        <div class="member-nickname">
            ${user.nickname}
        </div>
        <div class="member-teams">`;

    user.teamIds.forEach(teamId => {
            html += 
            `
                <a href="/teams?id=${teamId}">
                    <img class="team-icon" src="/teams/images?id=${teamId}&type=profile" alt="logo of team ${teamId}">
                </a>
            `
        });

    html += `
    </div>
    <a href="profile?username=${user.username}" class="view-member-button secondary-button button">View</a>
    </div>
    </div>`

    return html
}

function executePlayerSearch() {
    const searchbar = document.getElementById("player-searchbar")
    const searchTerm = searchbar.value

    const playersList = document.getElementById("player-list")
    playersList.innerHTML = ""

    fetch("users/search?un="+searchTerm)
        .then(response => response.json())
        .then(usersJson => {
            usersJson.forEach(userJson => {
                const html = generateUserCardHtml(userJson)
                playersList.innerHTML += html
            })
        })
}

executePlayerSearch()