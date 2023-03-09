package it.polito.did.gruppo8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

//gameViewModel è l'intermediatore tra le view (gli screens) & il database (gameManager)

//definisce funzioni che chiamano le funzioni del gameManager -> cambia loro il nome poi chiamato negli screen
//definisce variabili che chiamano le variabili del gameManager

//gli screen dovendo chiamare una funzione del gameManager non la chiamano con il vero nome ma con uno create qui nel ViewModel

//estende la classe ViewModel
class GameViewModel: ViewModel() {
    private val gameManager = GameManager(viewModelScope)

    fun onCreateNewGame() = gameManager.createNewGame()
    fun onJoinGame(matchId:String) = gameManager.joinGame(matchId)
    fun onStartGame() = gameManager.startGame()

    /*Schermata HouseOverviewScreen*/
    fun onViewItemList() = null /*TODO: restituisce lista di oggetti acquistati*/



    val players = gameManager.players
    val screenName = gameManager.screenName
    val matchId = gameManager.matchId

    /*Schermata HouseOverviewScreen*/
    val co2Impact = null /*TODO: parametro per barra co2*/
}
