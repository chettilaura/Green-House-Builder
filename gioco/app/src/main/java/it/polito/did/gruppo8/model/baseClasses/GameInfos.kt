package it.polito.did.gruppo8.model.baseClasses

class GameInfos() {
    var lobbyId : String? = null
    var cityName : String? = null
    var totalRounds : Int = 10
    //var totalTurns : Int = 2
    var quizTime : Int = 30
    var turnTime : Int = 60

    var turnCounter : Int = -1
    var roundCounter : Int = 0
    var currentPlayerId : String = ""
}