package it.polito.did.gruppo8.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import it.polito.did.gruppo8.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

import it.polito.did.gruppo8.ScreenName
import it.polito.did.gruppo8.model.baseClasses.*
import it.polito.did.gruppo8.util.myLibs.MyRandom
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class GameManager(private val scope: CoroutineScope/*, navController: NavController*/) {

    //region Fields

    private val _mutableCurrentScreenName = MutableLiveData<ScreenName>().also {
        it.value = ScreenName.Splash
    }
    val currentScreenName: LiveData<ScreenName> = _mutableCurrentScreenName

    /*
    private val _mutableLobbyId = MutableLiveData<String>()
    val lobbyId: LiveData<String> = _mutableLobbyId

    private val _mutableCityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _mutableCityName
     */
    private val _mutableGameInfos = MutableLiveData<GameInfos>().also {
        it.value = GameInfos()
    }
    val gameInfos: LiveData<GameInfos> = _mutableGameInfos
    private var _currentPlayerIndex: Int = -1

    private val _mutablePlayers = MutableLiveData<MutableMap<String, Player>>().also{
        it.value = mutableMapOf()
    }
    val players: LiveData<MutableMap<String, Player>> = _mutablePlayers

    private val _dbManager: DatabaseManager = DatabaseManager()

    //endregion

    //--------------------funzione init

    init {
        scope.launch {
            try {
                _dbManager.authenticate()
                delay(500)
                switchScreen(ScreenName.MainMenu)
            } catch (e: Exception) {
                switchScreen(ScreenName.Error)
            }
        }
    }


    //-------------------- methods GameManager

    /**
     * Switches the view to the corresponding screen of screenName.
     *
     * @param screenName the ScreenName class corresponding to the desired screen.
     * @param updateDatabase a Boolean telling if the screen switch must be notified to the Database. Default value is false.
     */

    fun switchScreen(screenName: ScreenName, updateDatabase: Boolean = false){
        try {
            if(updateDatabase){
                val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing match Id")
                _dbManager.writeData("$id/screen", screenName.route)
            }
            _mutableCurrentScreenName.value = screenName
            Navigator.navigateTo(screenName)
        }
        catch (e:Exception){
            Navigator.navigateTo(ScreenName.Error)
        }
    }

    //region Methods for Host

    fun createNewGame(cityName: String) {

        Log.d("createNewGame", "city name: $cityName")

        // VERSIONE RANDOM
        //val gameSessionId = MyRandom.string(5).uppercase()

        // VERSIONE DI DEBUG
        val gameSessionId = "test"

        try {
            _mutableGameInfos.value!!.lobbyId = gameSessionId
            _mutableGameInfos.value!!.cityName = cityName

            _dbManager.writeData(gameSessionId,
                mapOf(
                    "date" to LocalDateTime.now().toString(),
                    "hostId" to _dbManager.getCurrentUserID(),
                    "screen" to ScreenName.Waiting.route,
                    "gameInfos" to gameInfos.value,
                )
            )
            Log.d("GameManager", "Match creation succeeded")

            switchScreen(ScreenName.GameSetup)

            observePlayers()
        } catch (e: Exception) {
            switchScreen(ScreenName.Error)
        }
    }

    fun startGame() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        //Update Game settings
        _dbManager.writeData("$id/gameInfos", gameInfos.value)

        //Host switches to the Dashboard
        switchScreen(ScreenName.Dashboard)

        //Next turn
        nextTurn()

        //Coroutine to switch from WaitingQuiz to Quiz screen
        scope.launch {
            _dbManager.writeData("$id/screen", ScreenName.WaitingQuiz.route)
            delay(5000)
            _dbManager.writeData("$id/screen", ScreenName.Quiz.route)
        }

        //TODO: Test it
    }

    private fun nextTurn(){
        // Update turn counter
        val cachedInfos = _mutableGameInfos.value!!
        cachedInfos.turnCounter++
        if(cachedInfos.turnCounter==cachedInfos.totalTurns){
            _mutableGameInfos.value = cachedInfos
            Log.d("GameManager", "END GAME")
            //TODO: End Game
        }
        Log.d("GameManager", "Preparing ${cachedInfos.turnCounter} turn...")

        //Update next player id
        _currentPlayerIndex = (_currentPlayerIndex+1)%players.value!!.entries.size
        Log.d("GameManager", "NextTurn: player number = ${players.value!!.entries.size}")
        Log.d("GameManager", "NextTurn: player index = $_currentPlayerIndex")
        val newCurrentPlayer = players.value!!.entries.elementAtOrNull(_currentPlayerIndex)
            ?: throw RuntimeException("Errore nell'aggiornamento del prossimo player")
        cachedInfos.currentPlayerId = newCurrentPlayer.key
        _mutableGameInfos.value = cachedInfos

    }

    //endregion

    //region Methods for Client/Player

    fun joinGame(matchId:String, nickname:String) {

        try {
            if (matchId.isEmpty()) return

            val playerId = _dbManager.getCurrentUserID()
                ?: throw RuntimeException("User not authenticated to the database")
            Log.d("GameManager","joinGame: playerId is $playerId")

            scope.launch {
                //Check if matchId is correct
                val check = async { _dbManager.isDataPresent(matchId) }.await()
                if(!check) {
                    //TODO: riportare ad una schermata di errore per dire che la partita non esiste.
                    throw RuntimeException("Invalid gameId")
                }
                Log.d("GameManager","joinGame: matchId is $matchId")

                //Write player data on the database
                val playerObj = Player(playerId, nickname)
                _dbManager.writeData("$matchId/players/$playerId", playerObj)
                Log.d("GameManager","joinGame: player data written")

                //First read of GameInfos
                async {
                    _mutableGameInfos.value =
                        _dbManager.readData("$matchId/gameInfos", GameInfos::class.java)
                }.await()

                observeGameInfos()
                observePlayers()
                observeScreen()
            }
        } catch (e: Exception) {
            switchScreen(ScreenName.Error)
        }
    }

    //endregion

    //region Public shared Methods

    fun getRandomQuiz() : Quiz? {
        val totNum = runBlocking {
            _dbManager.readData("quiz/totNum", Int::class.java)
        }
        val quizId = MyRandom.int(0 until totNum!!)
        Log.d("TestQuiz", "Tot Quiz: $totNum, Range: ${0 until totNum}, Id: $quizId")
        return runBlocking {
            _dbManager.readData("quiz/$quizId", Quiz::class.java)
        }
    }

    //endregion

    //region Private shared Methods

    private fun observePlayers() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener("$id/players", "watchPlayers",
            onDataChange = { snapshot ->
                val currentPlayers = snapshot.getValue() as Map<*, *>?
                if(currentPlayers!=null){
                    Log.d("WatchPlayers","Update: Found type ${currentPlayers.javaClass.kotlin} -> $currentPlayers")

                    //Properly converting snapshot into a MutableMap<String,Player>
                    val tempMap = mutableMapOf<String, Player>()
                    for (childSnapshot in snapshot.children) {
                        val playerId = childSnapshot.key
                        val player = childSnapshot.getValue(Player::class.java)
                        if(playerId!=null && player!=null)
                            tempMap[playerId] = player
                    }
                    _mutablePlayers.value = tempMap

                    Log.d("WatchPlayers", "Data updated: ${_mutablePlayers.value!!.entries}")
                }
            },
            onCancelled = {
                switchScreen(ScreenName.Error)
            }
        )
    }



    //funzione che osserva il child "screen" della struttura firebase della partita
    //in base al valore di "screen" fa apparire la relativa schermata
    private fun observeScreen() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener("$id/screen", "watchScreen",
            onDataChange = {
                _mutableCurrentScreenName.value = ScreenName.routeToScreenName(it.value?.toString()?: "")
                switchScreen(_mutableCurrentScreenName.value ?: ScreenName.Error)
            },
            onCancelled = {
                switchScreen(ScreenName.Error)
            }
        )
    }

    private fun observeGameInfos() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener("$id/gameInfos", "watchGameInfos",
            onDataChange = {
                val currentGameInfos = it.getValue(GameInfos::class.java)
                if(currentGameInfos!=null)
                    _mutableGameInfos.value = currentGameInfos
            },
            onCancelled = {
                switchScreen(ScreenName.Error)
            }
        )
    }

    //endregion
}