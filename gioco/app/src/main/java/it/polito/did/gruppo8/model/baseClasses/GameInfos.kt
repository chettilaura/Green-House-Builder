package it.polito.did.gruppo8.model.baseClasses

class GameInfos() {
    var lobbyId : String? = null
    var cityName : String? = null
    //TODO: Valutare l'inserimento dei round
    var totalTurns : Int = 2
    var quizTime : Int = 30
    var turnTime : Int = 60

    var turnCounter : Int = 0
    var currentPlayerId : String = ""
}