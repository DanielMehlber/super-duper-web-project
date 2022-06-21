"use strict";

function removeUserConfirmation() {

    var check = confirm("Are you sure you to delete the User from the plattform?");
    document.getElementById("remove-user-button").click();
}


var element = element.getElementById("remove-user-button");
element.addEventListener("click", removeUserConfirmation());




