"use strict";

function removeUserConfirmation() {
    var check = confirm("Are you sure you to delete the User from the platform?");
    if (check === true){

    } else {

    }
}


let element = element.getElementById("remove-user-button");
element.addEventListener("click", removeUserConfirmation());




