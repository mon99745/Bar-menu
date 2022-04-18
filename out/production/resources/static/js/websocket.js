// 채팅방을 위한 websocket JS

var webSocket;
var nickname;
document.getElementById("name").addEventListener("click", function(){
    nickname = document.getElementById("nickname").value;
    document.getElementById("nickname").style.display="none";
    document.getElementById("name").style.display="none";
    connect();
})
document.getElementById("send").addEventListener("click",function(){
    send();
})
function connect(){
    webSocket = new WebSocket("ws://localhost:8080/chat");
    console.log(webSocket);
    webSocket.onopen = onOpen;
    webSocket.onclose = onClose;
    webSocket.onmessage = onMessage;
}
function disconnect(){
    webSocket.send(nickname + "님이 퇴장하셨습니다");
    webSocket.close();
}
function send(){
    msg = document.getElementById("message").value;
    webSocket.send(nickname + " : " + msg);
    document.getElementById("message").value = "";
}
function onOpen(){
    webSocket.send(nickname + "님이 입장하셨습니다.");
}
function onMessage(e){
    data = e.data;
    chatroom = document.getElementById("chatroom");
    chatroom.innerHTML = chatroom.innerHTML + "<br>" + data;
}
function onClose(){

}