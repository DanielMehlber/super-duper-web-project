/**
 * @param team.name name of team
 * @param team.id
 * @param team.slogan
 * @param team.tags
 * @param team.members
 */
function generateTeamCardHtml(team) {
    let html =
        `<a class="team-container" href="teams/team?id=${team.id}">
        <img alt="team logo" class="team-logo" src="teams/images?type=profile&id=${team.id}"
        <h2 class="team-title">${team.name}</h2>
        <p class="tags">${team.tags}</p>
        <div>
            <c:forEach items="${team.members}" var="member">
                <img class="player-logo" src="/users/images?type=profile&user=${member.username}" alt="player logo">
            </c:forEach>
        </div>
        <p class="member-count-paragraph">${team.value.size()} members</p>
        </a>`;

    return html
}

function executeTeamSearch() {
    const searchbar = document.getElementsByClassName("search-input")[0];
    const searchValue = searchbar.value;

    const teamList = document.getElementById("team-list");
    teamList.innerHTML = "";

    fetch("teams/search?un="+searchValue)
        .then(response => response.json())
        .then(teamJson => {
            teamJson.forEach(teamJson => {
                let html = generateTeamCardHtml(teamJson)
                teamList.innerHTML += html
            })
        });
}

executeTeamSearch();