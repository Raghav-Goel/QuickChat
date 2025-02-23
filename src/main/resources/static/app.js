const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/chat/room-1', (message) => {
        console.log("dfdddsd"+ JSON.parse(message.body));
        // showGreeting(JSON.parse(message.body););
        showGreeting(JSON.parse(message.body))
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

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
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    const message = {
        sender: "Raghav",
        textMessage: "hello world"
    };
    stompClient.publish({
        destination: "/app/SendMessage/room-1",
        body: JSON.stringify(message)
    });
}

function showGreeting(message) {
    console.log(message.sender+" "+message.textMessage);
    $("#greetings").append("<tr><td>" + message.sender+" "+message.textMessage + "</td></tr>");
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});