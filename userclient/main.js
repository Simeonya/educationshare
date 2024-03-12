class VideoSharing {
  constructor() {
    this.userVideoElement = document.querySelector('#localVideo');
    this.partnerVideoElement = document.querySelector('#remoteVideo');
    this.textAreaElement = document.querySelector("textarea");
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

}

const videoSharing = new VideoSharing();
videoSharing.getDisplayMedia();