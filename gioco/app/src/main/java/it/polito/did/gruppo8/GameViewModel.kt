package it.polito.did.gruppo8

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.polito.did.gruppo8.model.GameManager
import it.polito.did.gruppo8.model.baseClasses.Item
import it.polito.did.gruppo8.model.baseClasses.Quiz

class GameViewModel: ViewModel() {
    private val gameManager = GameManager(viewModelScope)

    val currentScreenName = gameManager.currentScreenName
    val gameInfos = gameManager.gameInfos

    val players = gameManager.players
    val myPlayerId = gameManager.myPlayerId

    val shop: LiveData<MutableMap<Int, Item>> = gameManager.shop

    var currentQuiz: Quiz = Quiz()

    // MainMenuScreen
    fun onNewGameButtonPressed() = gameManager.switchScreen(ScreenName.NewGame)
    fun onJoinGameButtonPressed() =  gameManager.switchScreen(ScreenName.JoinGame)

    // NewGameScreen
    fun onCreateCityButtonPressed(cityName: String) = gameManager.createNewGame(cityName)
    //fun onGameSetupButtonPressed() = null
    fun onStartButtonPressed(totalRounds: Int, turnTime: Int, quizTime: Int){
        gameManager.startGame(totalRounds, turnTime, quizTime)
    }

    // JoinGameScreen
    fun onCreateHouseButtonPressed(lobbyId:String, nickname:String) = gameManager.joinGame(lobbyId, nickname)

    // WaitingQuizScreen
    fun prepareNextQuiz(){
        currentQuiz = gameManager.getRandomQuiz()
    }

    // QuizScreen
    fun onSubmitAnswerButtonPressed(quiz: Quiz, answerIndex: Int) = gameManager.verifyQuiz(quiz,answerIndex)

    // HouseOverviewScreen
    fun onBuyButtonPressed(itemId: Int, free: Boolean = false) = gameManager.buyItemFromShop(itemId, free)
    fun onEndTurnButtonPressed() = gameManager.endTurn()
}
