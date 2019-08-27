var stompClient = null;
var socket = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    //var socket = new SockJS('/gs-guide-websocket');
	//var socket = new SockJS('http://localhost:8081/gs-guide-websocket');
	socket = new SockJS('http://localhost:9090/gs-guide-websocket');
	alert('http://localhost:9090/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
    
    console.log("!!! connected, session id: " + socket.sessionId);
    
    stompClient.heartbeat.outgoing = 1000;
    stompClient.heartbeat.incoming = 0;
    stompClient.reconnect_delay = 5000;

}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}



function getSession_id(){
    var sessionID = null;

    var strCookies = document.cookie;
    var cookiearray = strCookies.split(';')
    for(var i=0; i<cookiearray.length; i++){
      name = cookiearray[i].split('=')[0];
      value = cookiearray[i].split('=')[1];
      if(name == 'sid')
    	  sessionID = value;
    }
    
    return sessionID;
}

function sendName() {
    //stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
	//alert("sending1 news");
    //stompClient.send("/app/hello2", {}, JSON.stringify({'name': $("#name").val()}));
	
	
	
	var sessionId =getSession_id();
	var name = $("#name").val();
	var message = "Hello !" + name + "!!!!";
	
	 var payload = {
		        messageType    : 'CHAT',
		        senderSessionId: sessionId,
		        name           : name,
		        message        : message
		      };
	console.log("-----------------------------------");
	console.log("!!! Msg send, session id: " + sessionId);
	
	stompClient.send("/app/news", {}, JSON.stringify(payload) );
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

