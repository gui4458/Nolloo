

const username = document.querySelector('.chatId').value;
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
const msg = document.querySelector('#msg')
msg.addEventListener("keyup", function(e){
    if(e.keyCode == 13){
        e.preventDefault();
        document.querySelector(".send-btn").click();
    }
});


const websocket = new WebSocket("ws://192.168.30.107:8081/ws/chat");

websocket.onmessage = onMessage;
websocket.onopen = onOpen;
websocket.onclose = onClose;

function send(){
    console.log('send 함수 실행');

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
    // console.log("cur_session : ");

    //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
    if(sessionId == cur_session){
    //if(sessionId == 'admin'){
        var str = "<div class='col-6 my-msg'>";
        str += "<div class='alert alert-secondary justify-content-center;'>";
        str += "<span> </span><span class='msg-span'>" + sessionId + " : " + message + "</span>";
        str += "</div></div>";
        //$("#msgArea").append(str);
        document.querySelector('#msgArea').insertAdjacentHTML('beforeend', str);
    }
    else{



        fetch('/chatProfile', { //요청경로
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            //컨트롤러로 전달할 데이터
            body: JSON.stringify({
               // 데이터명 : 데이터값
            })
        })
        .then((response) => {
            return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            
        })
        //fetch 통신 실패 시 실행 영역
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
        
        
        

        var str = `<div class='col-6 other-msg'>
        <div class='alert alert-warning'>`
        str += `<span class='msg-span'> ${sessionId}  :  ${message} </span>
        </div></div>`
        //$("#msgArea").append(str);
        document.querySelector('#msgArea').insertAdjacentHTML('beforeend', str);
    }
    document.querySelector('#msgArea').scrollTop = document.querySelector('#msgArea').scrollHeight;
    console.log('onMessage 함수 끝');
}
