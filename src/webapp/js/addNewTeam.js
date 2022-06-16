function checkImageSize (input) {
    let saveButton = document.getElementsByName("enter-button")[0];
    const allowedSize = 16 * 1024;
    if (input.files[0].size > allowedSize) {
        saveButton.disabled = true;
        saveButton.className = 'disabled-enter-button';
        input.setCustomValidity("Invalid Field.");
        console.log("disabled")
    } else {
        saveButton.disabled = false;
        saveButton.className = 'enter-button';
        input.setCustomValidity("");
        console.log("enabled");
    }
}

function checkProfile() {
    let input = document.getElementsByName("profile")[0];
    input.innerHTML = 'Size to big';
    checkImageSize(input);
}

function checkBackground() {
    let input = document.getElementsByName("background")[0];
    checkImageSize(input);
}