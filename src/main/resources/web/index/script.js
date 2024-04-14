//TODO: Add token when login is successful
document.addEventListener('DOMContentLoaded', function () {
    const startLogin = document.getElementById('startLogin');
    const startRegister = document.getElementById('startRegister');

    const optionsDiv = document.getElementById('options');
    const loginDiv = document.getElementById('loginContainer');
    const registerDiv = document.getElementById('registerContainer');

    const backLogin = document.getElementById('backLogin');
    const backRegister = document.getElementById('backRegister');

    const finishLogin = document.getElementById('finishLogin');
    const finishRegister = document.getElementById('finishRegister');

    const startLoginFooter = document.getElementById('startLoginFooter');
    const startRegisterFooter = document.getElementById('startRegisterFooter');
    const reloadPage = document.getElementById('reloadPage');

    loginDiv.style.display = 'none';
    registerDiv.style.display = 'none';

    reloadPage.addEventListener('click', function () {
        window.location.reload();
    });

    startLoginFooter.addEventListener('click', function () {
        document.title = 'EducationShare | Login';
        optionsDiv.style.display = 'none';
        registerDiv.style.display = 'none';
        loginDiv.style.display = 'flex';
    });

    startLogin.addEventListener('click', function () {
        document.title = 'EducationShare | Login';
        optionsDiv.style.display = 'none';
        registerDiv.style.display = 'none';
        loginDiv.style.display = 'flex';
    });

    startRegisterFooter.addEventListener('click', function () {
        document.title = 'EducationShare | Register';
        optionsDiv.style.display = 'none';
        loginDiv.style.display = 'none';
        registerDiv.style.display = 'flex';
    });

    startRegister.addEventListener('click', function () {
        document.title = 'EducationShare | Register';
        optionsDiv.style.display = 'none';
        loginDiv.style.display = 'none';
        registerDiv.style.display = 'flex';
    });

    backLogin.addEventListener('click', function () {
        document.title = 'EducationShare | Welcome';
        optionsDiv.style.display = 'flex';
        loginDiv.style.display = 'none';
    });

    backRegister.addEventListener('click', function () {
        document.title = 'EducationShare | Welcome';
        optionsDiv.style.display = 'flex';
        registerDiv.style.display = 'none';
    });

    finishLogin.addEventListener('click', function (event) {
        const username = document.getElementById('loginUsername').value;
        const password = document.getElementById('loginPassword').value;

        event.preventDefault();
        login(username, password);
    });

    finishRegister.addEventListener('click', function (event) {
        const username = document.getElementById('registerUsername').value;
        const password = document.getElementById('registerPassword').value;
        const confirmPassword = document.getElementById('registerPassword2').value;

        if (password !== confirmPassword) {
            alert('Passwords do not match!');
            return;
        }

        event.preventDefault();
        register(username, password);
    });

    function register(username, password) {
        fetch('/api/v1/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password,
            }),
        }).then(response => {
            if (response.ok) {
                backRegister.click();
            } else {
                document.getElementById('registerDescription').style.color = 'red';
                document.getElementById('registerDescription').innerText = 'Username is already taken!';
            }
        });
    }

    function login(username, password) {
        fetch('/api/v1/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                password: password,
            }),
        }).then(response => {
            if (response.ok) {
                window.location.href = '/home';
            } else {
                document.getElementById('loginDescription').style.color = 'red';
                document.getElementById('loginDescription').innerText = 'Password or Username incorrect!';
            }
        });
    }


});