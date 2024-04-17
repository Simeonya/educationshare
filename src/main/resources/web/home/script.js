document.addEventListener('DOMContentLoaded', function () {
    const optionsDiv = document.getElementById('options');
    const headerDiv = document.getElementById('headerContainer');

    const manageAccount = document.getElementById('yourAccount');
    const manageRooms = document.getElementById('yourRooms');


    checkLogin();
    optionsDiv.style.display = 'none';
    headerDiv.style.display = 'none';
    manageAccount.style.display = 'none';
    manageRooms.style.display = 'none';

});

function checkLogin() {
    const token = localStorage.getItem('token');
    if (token) {

        checkTokenValidation();

        const optionsDiv = document.getElementById('options');
        const headerDiv = document.getElementById('headerContainer');
        const manageAccount = document.getElementById('yourAccount');
        const manageRooms = document.getElementById('yourRooms');
        optionsDiv.style.display = 'block';
        headerDiv.style.display = 'block';
        manageAccount.style.display = 'block';
        manageRooms.style.display = 'block';
    }
}

function checkTokenValidation() {
    console.log('Checking token validation');
    const token = localStorage.getItem('token');

    fetch('/api/auth/validate', {
        method: 'POST', headers: {
            'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token
        }
    }).then(response => {
        if (response.ok) {
            console.log('Token is valid');
        } else {
            console.log('Token is invalid');
            localStorage.removeItem('token');
            alert('You are not logged in or your session has expired');
            document.location.href = '/';
        }
    });
}
