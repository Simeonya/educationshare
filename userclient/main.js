const userVideoElement = document.querySelector('#localVideo');
const partnerVideoElement = document.querySelector('#remoteVideo');
const textAreaElement = document.querySelector("textarea");

const peerConnection = new RTCPeerConnection({
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

peerConnection.onicecandidate = (event) => {
  if (event.candidate) {
    console.log(JSON.stringify(event.candidate));
  }
};

peerConnection.ontrack = (event) => {
  partnerVideoElement.srcObject = event.streams[0];
};

const displayMediaOptions = {
  video: {
    cursor: "always",
    frameRate: { max: 15 },
    height: { max: 720 }
  },
  audio: false
};

async function getDisplayMedia() {
  const stream = await navigator.mediaDevices.getDisplayMedia(displayMediaOptions);
  userVideoElement.srcObject = stream;
  peerConnection.addStream(userVideoElement.srcObject);
}

getDisplayMedia().catch(() => {
  console.log('errors with the media device');
});

function createOffer() {
  peerConnection.createOffer({
    mandatory: {
      offerToReceiveAudio: false,
      offerToReceiveVideo: true
    },
  })
    .then(sessionDescription => {
      console.log(JSON.stringify(sessionDescription));
      peerConnection.setLocalDescription(sessionDescription);
    });
}

function setRemoteDescription() {
  const description = JSON.parse(textAreaElement.value);
  peerConnection.setRemoteDescription(new RTCSessionDescription(description));
}

function createAnswer() {
  peerConnection.createAnswer({
    mandatory: {
      offerToReceiveAudio: false,
      offerToReceiveVideo: true
    }
  })
    .then(sessionDescription => {
      console.log(JSON.stringify(sessionDescription));
      peerConnection.setLocalDescription(sessionDescription);
    });
}

function addCandidate() {
  const candidate = JSON.parse(textAreaElement.value);
  peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
}