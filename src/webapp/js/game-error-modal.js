// Author: Daniel Mehlber

"use strict";

function closeErrorModal() {
  const errorModalContainer = document.getElementById("error-modal-container");
  if(errorModalContainer)
    errorModalContainer.classList.add("invisible")
}

function init() {
  const closeButton = document.getElementById("error-modal-close");
  if(closeButton)
    closeButton.addEventListener("click", closeErrorModal)
  const errorModalContainer = document.getElementById("error-modal-container");
  if(errorModalContainer)
    errorModalContainer.addEventListener("click", closeErrorModal)
}

document.addEventListener("DOMContentLoaded", init)
