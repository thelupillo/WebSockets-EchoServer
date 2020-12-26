var ws;
var uri = "ws://" + document.domain + ":4567/echo";;
function connect() {
	this.ws = new WebSocket(uri);
	ws.onopen = function(event) {
		console.log(event);
		document.getElementById("chatForm").style.display = "block";
	};

	ws.onmessage = function(event) {
		console.log(event);
		var pMessage = document.createElement('p');
		pMessage.innerText = event.data;
		document.getElementById("messages").appendChild(pMessage);
		document.getElementById("messages").scrollTop = document.getElementById("messages").scrollHeight;
	};

	ws.onclose = function(event) {
		console.log(event);
		console.log('Socket is closed. Reconnect will be attempted in 1 second.', event.reason);
		setTimeout(function () {
			connect();
		}, 1000);
		document.getElementById("chatForm").style.display = "none";
	};

	ws.onerror = function(error) {
		console.log(error);
		console.error('Socket encountered error: ', error.message, 'Closing socket');
		ws.close();
	};
}

function sendMessage(message) {
	this.ws.send(message);
}

function sendFromTextBox() {
	sendMessage(document.getElementById("textBox").value);
	document.getElementById("textBox").value = "";
}

connect();