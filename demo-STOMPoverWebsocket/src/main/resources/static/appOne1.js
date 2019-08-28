var wsClient = null;

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

function guid() {
	  function s4() {
	    return Math.floor((1 + Math.random()) * 0x10000)
	      .toString(16)
	      .substring(1);
	  }
	  return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
	    s4() + '-' + s4() + s4() + s4();
}

function getData() {
	var senderGuid =guid();
	var name = $("#name").val();
	var message = "Hello !" + name + "!!!!";
	
	 var payload = {
		        messageType    : 'CHAT',
		        senderGUID     : senderGuid,
		        name           : name,
		        message        : message
		      };
	console.log("-----------------------------------");
	console.log("!!! Msg send, senderGuid id: " + senderGuid);
	return payload;
}


function getSessionId(socket) {
     var simpSessionId;
    if (socket !== null) {
        var strURL=socket._transport.url;
        var iPos= strURL.lastIndexOf( "/" )
        if (iPos !=-1) {
          simpSessionId= strURL.substring(iPos+1,strURL.length )
        }
    }
    return simpSessionId;
}

function connect() {
    //var socket = new SockJS('/gs-guide-websocket');
	//var socket = new SockJS('http://localhost:8081/gs-guide-websocket');
	var sendName=$("#name").val();
	var socket = new SockJS('http://localhost:9080/one');
	alert('http://localhost:9080/one sendName:'+ sendName);
    wsClient = Stomp.over(socket);
    wsClient.connect({name: sendName }, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        console.log('===========socket.id: '+ socket._transport.url);


        console.log("connected, session id: " + getSessionId(socket));
        console.log("===============================================================");

        wsClient.subscribe('/user/queue'+ sendName +'/errors', function (greeting) {
            alert('Error !:' + greeting.body);
        });

        wsClient.subscribe('/user/queue/'+ sendName +'/reply', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
    wsClient.heartbeat.outgoing = 1000;
    wsClient.heartbeat.incoming = 0;    
}

function disconnect() {
    if (wsClient !== null) {
        //wsClient.unsubscribe();
        wsClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    //wsClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
	//alert("sending1 news");
    //wsClient.send("/app/hello2", {}, JSON.stringify({'name': $("#name").val()}));
	wsClient.send("/app/message", {}, JSON.stringify({'name': $("#name").val()}));
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