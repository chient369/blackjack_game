'use strict';
$(document).ready(function() {
    setTimeout(function() {
        $('.table-background-logo').fadeOut()
        $('#menu-content').fadeIn();
    }, 1000);
});

//menu js
$('#menu-btn').click(function() {
        $('#menu-content').toggle();
    })
    //exit btn
$('.exit-btn').click(function() {
        $('#menu-content').hide();
    })
    //join game of form 

$('.join_game-btn').click(function() {
    $('#menu-content').hide();
    $('#menu_connect_game_form').show();
})

$('#join_back-btn').click(function() {
    $('#menu_connect_game_form').hide();
    $('#menu-content').show();
})


var roomId;
const id = document.querySelector('#playerId').innerHTML

function displayInfo(data) {
    var players = data.players;
    roomId = data.roomId;
    let playerAmount = players.length;
    let yourIndex = 0;
    for (let i = 0; i < playerAmount; i++) {
        let player = players[i];
        if (players[i].playerId === id) {
            displayCurrentPlayer(player, roomId, yourIndex);
            yourIndex = i;
            break;
        }
    }
    if (playerAmount > 1) {
        var index = 1;
        for (let i = (yourIndex + 1) % playerAmount; i < playerAmount; i = (i + 1) % playerAmount) {
            let player = players[i];
            if (i != yourIndex) {
                displayRoom(player, index);
                index++;
            } else {
                break;
            }

        }
    }
}

function displayCurrentPlayer(player, roomId, index) {
    let role = player.gameRole;
    let idElement = document.querySelector('#rid');
    $('.header-info_item:first').show();
    idElement.innerText = 'ROOM' + roomId;
    idElement.style.visibility = "";
    // document.querySelector('#pname').innerText = player.playerName;
    // document.querySelector('#pwallet').innerText = player.wallet;
    if (role === 'MASTER') {
        var dealerIndex = '.dealer-' + (index + 1);
        $(dealerIndex).show();
        $('.master_opera').show();
    } else {
        $('#menu_connect_game_form').hide();
        $('.joiner_opera').show();
    }

    $('#menu-content').hide();
}

function displayRoom(player, index) {

    let role = player.gameRole;
    let pInfoNode = '.player_info-' + (index + 1);

    let pInfo = document.querySelector(pInfoNode);
    let infoNode = pInfo.getElementsByTagName('p');
    infoNode[0].innerText = player.playerName;
    infoNode[1].innerText = player.user.wallet;
    if (role === 'MASTER') {
        var dealerIndex = '.dealer-' + (index + 1);
        $(dealerIndex).show();
    }
    if (role === 'JOINER') {
        var dealerIndex = '.dealer-' + (index + 1);
        $(dealerIndex).hide();
    }

    let playerEle = '.player_' + (index + 1);
    $(playerEle).show();
    role = '';
}

function bet() {
    var betAmount = document.querySelector('#bet-input').value;
    $('.bet_amount-1').html(betAmount + '$');
    $('.bet_amount-1').show();
    $('#bet-input').hide();
    $('#bet-btn').hide();

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
        success: function(data) {
          
        },
        error: function(error) {
            console.log(error);
        }


    })
}

function startGameDisplay(data) {
    var cardList = data.playersCards[id];
    for (let i = 0; i < cardList.length; i++) {
        var card = cardList[i];
        let no = card.no;
        let suit = card.suit;
        $(".table-hand_cards").append("<card-t rank=" + no + " suit=" + suit + "></card-t>")


    }


}