const goBackButton = document.getElementById('indexPage');
document.addEventListener('DOMContentLoaded', function () {
    goBackButton.addEventListener('click', function (event) {
        event.preventDefault();
        window.location.href = '/';
    });
});