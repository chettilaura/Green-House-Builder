package it.polito.did.gruppo8.model.baseClasses

class GameInfos() {
    var lobbyId : String? = null
    var cityName : String? = null
    var totalTurns : Int = 0
    var quizTime : Int = 15
    var turnTime : Int = 30

    var turnCounter : Int = 0
    var currentPlayer : String = ""
}