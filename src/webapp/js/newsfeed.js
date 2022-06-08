/**
 * Puts date in printable format
 * @param date date string
 * @returns {string} a formatted version of date
 */
function formatDate(date) {
    const formattedDate = date.toLocaleString("en-GB", {
        day: "numeric",
        month: "short",
        year: "numeric",
        hour: "numeric",
        minute: "2-digit"
    });

    return formattedDate
}

/**
 * Generates news feed item for a user registration
 * @param item.player1 id of player who has joined
 * @param item.date date of registration
 */
function generatePlayerRegisteredNewsHTML(item) {
    let s = `
    <div class="newsfeed-item">
         <div class="newsfeed-images">
              <img src="users/images?user=${item.player1}"/>
         </div>
         <div class="news-description">
             🎉 Player @<a href="profile?username=${item.player1}" class="player-ref">${item.player1}</a> has joined the platform! 🎉
         </div>
         <div class="news-time">
             ${formatDate(new Date(item.date))}
         </div>
    </div>`

    return s
}

/**
 * generates html of newsfeed item for newly created teams
 * @param item.team1
 * @param item.date
 * @returns {string}
 */
function generateTeamCreatedNewsHTML(item) {
    let s = `
    <div class="newsfeed-item">
         <div class="newsfeed-images">
              <img src="teams/images?id=${item.team1}"/>
         </div>
         <div class="news-description">
             🎉 <a href="teams/team?id=${item.team1}" class="player-ref">A new Team </a> has been founded 🎉 Click <a href="teams/team?id=${item.team1}" class="player-ref">here</a> to join
         </div>
         <div class="news-time">
             ${formatDate(new Date(item.date))}
         </div>
    </div>`

    return s
}

/**
 * Adds item to newsfeed
 * @param item.type type of newsfeed item
 */
function addNewsfeedItem(item) {
    let html = ""
    switch (item.type) {
        case "player-join":
            html = generatePlayerRegisteredNewsHTML(item)
            break
        case "team-created":
            html = generateTeamCreatedNewsHTML(item)
    }

    let newsfeed = document.getElementById("newsfeed")
    newsfeed.innerHTML += html
}

function loadMoreNewsfeedItems() {
    fetch("newsfeed/fetch?amount=10")
        .then(response => response.json())
        .then(items => {
            items.forEach(item => {
                addNewsfeedItem(item)
            })
        })
}