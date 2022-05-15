
function showNav() {
    document.getElementById("main").style.left = '200px';
}

function hideNav() {
    document.getElementById("main").style.left = '0px';
}

function toggleNav() {
    const nav = document.getElementById("main")
    if(nav.style.left != '0px') {
        hideNav()
    } else {
        showNav()
    }
}