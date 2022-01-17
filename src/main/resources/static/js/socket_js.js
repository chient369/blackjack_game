const url = 'http://localhost:8080';
let stompClient;
var roomId;
var pname = document.querySelector('#pname').innerHTML.trim();
var pid = document.querySelector('#playerId').innerHTML.trim();

function connectToSocket(rommId) {
    console.log("connecting to the room");
    let socket = new SockJS(url + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + rommId, function(response) {
            let data = JSON.parse(response.body);
            console.log(data)
            if (data.status == 'WAIT') {
                var players = data.players;
                var playerName = players[players.length - 1].playerName;
                alert(playerName + ' entered the room')
                displayInfo(data);
            }
            if (data.status == 'IN_PROCESS') {
                startGameDisplay(data);
            }
        })
    });
}


function create_room() {
    $.ajax({
        url: url + "/api/create-room",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "name": pname,
            "playerId": pid
        }),
        success: function(data) {
            connectToSocket(data.roomId);
            displayInfo(data);
            console.log(data)
        },
        error: function(error) {
            console.log(error);
        }
    })
}


function connectToGame() {
    console.log('conneting.....')
    let roomId = document.querySelector('#roomId').value.toUpperCase();
    if (roomId == null || roomId === '') {
        alert("Please enter room ID");
    } else {
        $.ajax({
            url: url + "/api/connect-room",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "roomId": roomId,
                "player": {
                    "name": pname,
                    "playerId": pid
                }
            }),
            success: function(data) {
                connectToSocket(data.roomId);
                alert('Connected to game : ' + data.roomId);
                displayInfo(data);
            },
            error: function(error) {
                console.log(error);
            }
        })
    }

}

function startGame() {
    $('.master_opera').hide();
    $.ajax({
        url: url + '/api/start',
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            'roomId': roomId
        }),
        success: function(data) {},
        error: function(error) {
            console.log(error);
        }


    })
}