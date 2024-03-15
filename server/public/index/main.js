class Index {
  constructor() {
    this.registerButton = document.getElementById('register');
    this.teacherButton = document.getElementById('teacher');
    this.backButton = document.getElementById('backButton');
    this.connectButton = document.getElementById('start');

    this.teacherButton.onclick = () => {
      document.getElementById('optionsContainer').style.display = 'none';
      document.getElementById('registerContainer').style.display = 'block';
      event.preventDefault();
    }

    this.connectButton.onclick = () => {
      document.location.href = '/userclient/';
      event.preventDefault();
    }

    this.registerButton.onclick = () => {
      this.register();
      event.preventDefault();
    }

    document.addEventListener('DOMContentLoaded', (event) => {
      document.getElementById('registerContainer').style.display = 'none';
    });
  }

  register() {
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('password2').value;

    if (!firstName || !lastName || !email || !password || !confirmPassword) {
      alert('Please fill out all fields');
      return;
    }

    if (password !== confirmPassword) {
      alert('Passwords do not match');
      return;
    }

    /**
     * Send the registration request to the server
     * Not implemented yet
     */

  }
}

class DarkModeChanger {
  constructor() {
    this.darkSwitch = document.getElementById("darkSwitch");
    if (this.darkSwitch) {
      this.initTheme();
      this.darkSwitch.addEventListener("change", () => this.resetTheme());
    }
  }

  initTheme() {
    var darkThemeSelected =
      localStorage.getItem("darkSwitch") !== null &&
      localStorage.getItem("darkSwitch") === "dark";
    this.darkSwitch.checked = darkThemeSelected;
    darkThemeSelected
      ? document.body.setAttribute("data-theme", "dark")
      : document.body.removeAttribute("data-theme");
  }

  resetTheme() {
    if (this.darkSwitch.checked) {
      document.body.setAttribute("data-theme", "dark");
      localStorage.setItem("darkSwitch", "dark");
    } else {
      document.body.removeAttribute("data-theme");
      localStorage.removeItem("darkSwitch");
    }
  }
}

new Index();
new DarkModeChanger();