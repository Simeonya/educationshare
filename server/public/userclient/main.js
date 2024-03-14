class VideoSharing {
  constructor() {
    this.userVideoElement = document.querySelector('#localVideo');
    this.partnerVideoElement = document.querySelector('#remoteVideo');
    this.textAreaElement = document.querySelector("textarea");
    this.confirmButton = document.getElementById('joinButton');
    this.shareButton = document.getElementById('shareButton');
    this.video = document.getElementById('localVideo');
    this.slider = document.getElementById('sizeSlider');
    this.isSharing = false;

    this.peerConnection = new RTCPeerConnection({
      configuration: {
        offerToReceiveAudio: false,
        offerToReceiveVideo: true
      },
      iceServers: [
        {
          // example
          urls: "turn:turn.example.org",
          username: "user",
          credential: "myPassword"
        },
      ]
    });

    this.peerConnection.onicecandidate = (event) => {
      if (event.candidate) {
        console.log(JSON.stringify(event.candidate));
      }
    };

    this.peerConnection.ontrack = (event) => {
      this.partnerVideoElement.srcObject = event.streams[0];
    };

    this.displayMediaOptions = {
      video: {
        cursor: "always",
        frameRate: { max: 15 },
        height: { max: 720 }
      },
      audio: false
    };

    this.slider.oninput = () => {
      this.video.style.width = this.slider.value + 'px';
    }

    this.confirmButton.onclick = () => {

      if (document.getElementById('roomUsername').value === '') {
        alert('Please enter your name');
        return;
      }

      if (document.getElementById('roomAddress').value === '') {
        alert('Please enter the server address');
        return;
      }

      if (document.getElementById('roomPassword').value === '') {
        alert('Please enter the server password');
        return;
      }

      document.getElementById('userData').style.display = 'none';
      document.getElementById('screenShare').style.display = 'block';

      this.share();
    }

    this.shareButton.onclick = () => {
      this.share();
    }

    document.addEventListener('DOMContentLoaded', (event) => {
      document.getElementById('screenShare').style.display = 'none';
    });

  }

  async getDisplayMedia() {
    if (this.isSharing) {
      return;
    }
    this.isSharing = true;
    try {
      const useAudio = false; // not implemented yet
      const stream = await navigator.mediaDevices.getDisplayMedia({
        ...this.displayMediaOptions,
        audio: useAudio
      });
      this.userVideoElement.srcObject = stream;
      this.peerConnection.addStream(this.userVideoElement.srcObject);
    } catch (error) {
      console.log('errors with the media device');
      console.log('===========================================')
      console.error(error);
      console.log('===========================================')
      this.isSharing = false;
    }
  }

  createOffer() {
    this.peerConnection.createOffer({
      mandatory: {
        offerToReceiveAudio: false,
        offerToReceiveVideo: true
      },
    })
      .then(sessionDescription => {
        console.log(JSON.stringify(sessionDescription));
        this.peerConnection.setLocalDescription(sessionDescription);
      });
  }

  setRemoteDescription() {
    const description = JSON.parse(this.textAreaElement.value);
    this.peerConnection.setRemoteDescription(new RTCSessionDescription(description));
  }

  createAnswer() {
    this.peerConnection.createAnswer({
      mandatory: {
        offerToReceiveAudio: false,
        offerToReceiveVideo: true
      }
    })
      .then(sessionDescription => {
        console.log(JSON.stringify(sessionDescription));
        this.peerConnection.setLocalDescription(sessionDescription);
      });
  }

  addCandidate() {
    const candidate = JSON.parse(this.textAreaElement.value);
    this.peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
  }

  share() {
    if (this.isSharing) return;
    this.getDisplayMedia();
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


new VideoSharing();
new DarkModeChanger();
