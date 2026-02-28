let stompClient = null;

function initWebSocketForLastTransactions() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.debug = null;

    stompClient.connect({}, function() {
        console.log('Connected to WebSocket');

        stompClient.subscribe('/topic/transactions', function(message) {
            const transaction = JSON.parse(message.body);
            loadDataOfAllTransactions(10);
        });
    });
}

function disconnectWebSocket() {
    if (stompClient !== null) {
        stompClient.disconnect();
        console.log('Disconnected from WebSocket');
    }
}