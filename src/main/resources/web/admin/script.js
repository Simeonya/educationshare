document.addEventListener('DOMContentLoaded', function () {
    const optionsDiv = document.getElementById('options');
    const loginDiv = document.getElementById('loginContainer');
    const headerDiv = document.getElementById('headerContainer');
    const finishLogin = document.getElementById('finishLogin');

    const manageUsers = document.getElementById('manageUsers');
    const manageRooms = document.getElementById('manageRooms');

    optionsDiv.style.display = 'none';
    headerDiv.style.display = 'none';
    loginDiv.style.display = 'flex';

    finishLogin.addEventListener('click', function (event) {
        event.preventDefault();
        const pin = document.getElementById('loginPin').value;
        if (checkPin(pin)) {
            alert('You are now logged in');
        } else {
            alert('Invalid PIN');
        }
    });

    manageUsers.addEventListener('click', function (event) {
        manageUser();
    });

    manageRooms.addEventListener('click', function (event) {
        manageRoom();
    });

    function checkPin(pin) {
        if (pin === '1234') {
            optionsDiv.style.display = 'flex';
            headerDiv.style.display = 'flex';
            loginDiv.style.display = 'none';
            return true;
        } else {
            return false;
        }
    }

    function manageUser() {
        const pin = document.getElementById('loginPin').value;

        if (!checkPin(pin)) {
            alert('You are not authorized to access this page');
            return;
        }

        alert('Manage Users');

        //TODO Implement the user management
    }

    function manageRoom() {
        const pin = document.getElementById('loginPin').value;

        if (!checkPin(pin)) {
            alert('You are not authorized to access this page');
            return;
        }

        alert('Manage Rooms');

        //TODO: Implement the room management
    }
});