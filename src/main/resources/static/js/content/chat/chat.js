const username = '';

// $("#disconn").on("click", (e) => {
//     disconnect();
// })

// document.querySelector('#disconn').addEventListener((e) => {
//     disconnect();
// });


// $("#button-send").on("click", (e) => {
//     send();
// });

document.querySelector('#button-send').addEventListener('click', (e) => {
    send();
});

const websocket = new WebSocket("ws://192.168.30.107:8081/ws/chat");

websocket.onmessage = onMessage;
websocket.onopen = onOpen;
websocket.onclose = onClose;

function send(str){
    console.log('send 함수 실행');
    console.log('11111' + str);

    let msg = document.getElementById("msg");

    // console.log(msg.value);
    websocket.send(username + ":" +  msg.value);
    msg.value = '';
    console.log('send 함수 끝');
}

//채팅창에서 나갔을 때
function onClose(evt) {
    console.log('onClose 함수 실행');
    var str =  username +  ": 님이 방을 나가셨습니다.";
    websocket.send(str);
    console.log('onClose 함수 끝');
}

//채팅창에 들어왔을 때
function onOpen(evt) {
    console.log('onOpen 함수 실행');
    var str = username +  ": 님이 입장하셨습니다.";
    websocket.send(str);
    console.log('onOpen 함수 끝');
}

function onMessage(msg) {
    console.log('onMessage 함수 실행');
    console.log(msg);
    var data = msg.data;
    console.log(msg.data);
    var sessionId = null;
    //데이터를 보낸 사람
    var message = null;
    var arr = data.split(":");

    for(var i=0; i<arr.length; i++){
        console.log('arr[' + i + ']: ' + arr[i]);
    }

    var cur_session = username;

    //현재 세션에 로그인 한 사람
    console.log("cur_session : " + cur_session);
    //console.log("cur_session : ");
    sessionId = arr[0];
    message = arr[1];

    console.log("sessionID : " + sessionId);
    console.log("cur_session : " + cur_session);
    //console.log("cur_session : ");

    //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
    if(sessionId == cur_session){
    //if(sessionId == 'admin'){
        var str = "<div class='col-6 my-msg'>";
        str += "<div class='alert alert-secondary'>";
        str += "<b>" + sessionId + " : " + message + "</b>";
        str += "</div></div>";
        //$("#msgArea").append(str);
        document.querySelector('#msgArea').insertAdjacentHTML('beforeend', str);
    }
    else{
        var str = "<div class='col-6 other-msg'>";
        str += "<div class='alert alert-warning'>";
        str += "<b>" + "son" + " : " + message + "</b>";
        str += "</div></div>";
        //$("#msgArea").append(str);
        document.querySelector('#msgArea').insertAdjacentHTML('beforeend', str);
    }

    console.log('onMessage 함수 끝');
}

function goPopup(){
    
    // window.open("http://192.168.30.107:8081/chat", "mozillaWindow", "popup");
    const windowFeatures = "left=1500,top=100,width=600,height=600";
const handle = window.open(
    "http://192.168.30.107:8081/chat",
    "mozillaWindow",
    windowFeatures,
    "popup"
);
}