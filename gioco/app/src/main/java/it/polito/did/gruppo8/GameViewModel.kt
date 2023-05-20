package it.polito.did.gruppo8

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.rememberNavController
import it.polito.did.gruppo8.model.GameManager
import it.polito.did.gruppo8.model.baseClasses.Quiz

//gameViewModel è l'intermediatore tra le view (gli screens) & il database (gameManager)

//definisce funzioni che chiamano le funzioni del gameManager -> cambia loro il nome poi chiamato negli screen
//definisce variabili che chiamano le variabili del gameManager

//gli screen dovendo chiamare una funzione del gameManager non la chiamano con il vero nome ma con uno create qui nel ViewModel

//estende la classe ViewModel
class GameViewModel: ViewModel() {
    private val gameManager = GameManager(viewModelScope)

    val currentScreenName = gameManager.currentScreenName
    val gameInfos = gameManager.gameInfos

    val players = gameManager.players
    val myPlayerId = gameManager.myPlayerId

    val shop = gameManager.shop

    var currentQuiz: Quiz = Quiz()

    // MainMenuScreen
    fun onNewGameButtonPressed() = gameManager.switchScreen(ScreenName.NewGame)
    fun onJoinGameButtonPressed() =  gameManager.switchScreen(ScreenName.JoinGame)

    // NewGameScreen
    fun onCreateCityButtonPressed(cityName: String) = gameManager.createNewGame(cityName)
    //fun onGameSetupButtonPressed() = null
    fun onStartButtonPressed(){
        gameManager.startGame()
    }

    // JoinGameScreen
    fun onCreateHouseButtonPressed(lobbyId:String, nickname:String) = gameManager.joinGame(lobbyId, nickname)

    // WaitingQuizScreen
    fun prepareNextQuiz(){
        currentQuiz = gameManager.getRandomQuiz()
    }

    // QuizScreen
    fun onSubmitAnswerButtonPressed(quiz: Quiz, answerIndex: Int) = gameManager.verifyQuiz(quiz,answerIndex)
}
