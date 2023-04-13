package it.polito.did.gruppo8

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.polito.did.gruppo8.model.GameManager

//gameViewModel è l'intermediatore tra le view (gli screens) & il database (gameManager)

//definisce funzioni che chiamano le funzioni del gameManager -> cambia loro il nome poi chiamato negli screen
//definisce variabili che chiamano le variabili del gameManager

//gli screen dovendo chiamare una funzione del gameManager non la chiamano con il vero nome ma con uno create qui nel ViewModel

//estende la classe ViewModel
class GameViewModel: ViewModel() {
    private val gameManager = GameManager(viewModelScope)

    val players = gameManager.players
    val currentScreenName = gameManager.currentScreenName
    val lobbyId = gameManager.lobbyId

    /*Schermata HouseOverviewScreen*/
    val co2Impact = null /*TODO: parametro per barra co2*/

    fun onCreateNewGame() = gameManager.createNewGame()
    //OnPreJoinGame: cambia la schermata alla screen JoinGame
    //TODO: Ripensare ai nomi di queste operazioni, troppo fuorvianti e confusi -Mattia
    fun onPreJoinGame() = gameManager.preJoinGame()

    //OnJoinGame: aggiunge il player alla partita
    fun onJoinGame(lobbyId:String, nickname:String) = gameManager.joinGame(lobbyId, nickname)

    fun onStartGame() = gameManager.startGame()

    /*Schermata HouseOverviewScreen*/
    fun onViewItemList() = null /*TODO: restituisce lista di oggetti acquistati*/

}
