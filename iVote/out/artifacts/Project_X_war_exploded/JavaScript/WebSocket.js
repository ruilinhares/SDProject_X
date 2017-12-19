var websocket = null;

window.onload = function() { // URI = ws://10.16.0.165:8080/WebSocket/ws
    connect('ws://' + window.location.host + 'src/ws');
}

function connect(host) { // connect to the host websocket
    if ('WebSocket' in window)
        websocket = new WebSocket(host);
    else if ('MozWebSocket' in window)
        websocket = new MozWebSocket(host);
    else {
        writeToHistory('Get a real browser which supports WebSocket.');
        return;
    }

    websocket.onopen    = onOpen; // set the event listeners below
    websocket.onclose   = onClose;
    websocket.onmessage = onMessage;
    websocket.onerror   = onError;
}

function onOpen(event) {
    console.info('Connected to ' + window.location.host + '.');
}

function onClose(event) {
    console.info('WebSocket closed.');
}

function onMessage(message) { // print the received message
    formatMessage(message.data);
}

function onError(event) {
    console.info('WebSocket error (' + event.data + ').');
}

function doSend() {
    var message = document.getElementById('chat').value;
    if (message != '')
        websocket.send(message); // send the message
    document.getElementById('chat').value = '';

}

function writeToHistory(text) {

}

function formatMessage(msg) {
    var text;
    if(msg.startsWith("{")) {
        var jsonMsg = JSON.parse(msg),
            type = jsonMsg.type,
            pathname = window.location.pathname;

        console.log(msg);

        switch(type) {
            case "detailID":
                if(pathname.includes("/auctionDetail.jsp")) {
                    $("#watchUsers").text(jsonMsg.count);
                }
                break;
            case "userLogin":
                var name = jsonMsg.username,
                    stName = $("#USERNAME").text();
                if(pathname.includes("/onlineUsers.jsp") && name != stName) {
                    addNewUser(name);
                }
                break;
            case "userLogout":
                if(pathname.includes("/onlineUsers.jsp")) {
                    removeUser(jsonMsg.username);
                }
                break;
            case "bid":
            case "message":
                displayNotification(jsonMsg);
                break;
            default:
                console.info(type);
        }
    } else {
        console.info(msg);
    }
}

function displayNotification(jsonObj) {
    var id = jsonObj.id,
        message = jsonObj.message,
        text = "<p><a target='_blank' href='" + window.location.host + "/iBeiProject/auctionDetail.action?auctionID=" + id + "'>"+ message +"</a></p>";
    writeToHistory(text);
}
