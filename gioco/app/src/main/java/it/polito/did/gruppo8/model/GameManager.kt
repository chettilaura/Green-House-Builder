package it.polito.did.gruppo8.model

import android.util.Log
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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
import kotlin.random.Random

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

    private val _mutablePlayers = MutableLiveData<Map<String, Player>>().also{
        it.value = emptyMap()
    }
    val players: LiveData<Map<String, Player>> = _mutablePlayers

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


    //-------------------- public methods GameManager
    //region Public Methods

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
                    "gameInfos" to gameInfos.value
                )
            )
            Log.d("GameManager", "Match creation succeeded")

            switchScreen(ScreenName.GameSetup)

            watchPlayers()
        } catch (e: Exception) {
            switchScreen(ScreenName.Error)
        }
    }

    fun joinGame(matchId:String, nickname:String) {

        try {
            if (matchId.isEmpty()) return

            val playerId = _dbManager.getCurrentUserID()
                ?: throw RuntimeException("User not authenticated to the database")
            Log.d("GameManager","joinGame: playerId is $playerId")

            scope.launch {
                val check = async { _dbManager.isDataPresent(matchId) }.await()
                if(!check) {
                    //TODO: riportare ad una schermata di errore per dire che la partita non esiste.
                    throw RuntimeException("Invalid gameId")
                }
                Log.d("GameManager","joinGame: matchId is $matchId")

                val playerObj = Player(playerId, nickname)
                _dbManager.writeData("$matchId/players/$playerId", playerObj)
                Log.d("GameManager","joinGame: player data written")

                async {
                    _mutableGameInfos.value =
                        _dbManager.readData("$matchId/gameInfos", GameInfos::class.java)
                }.await()

                _dbManager.addListener("$matchId/gameInfos", "watchGameInfos",
                    onDataChange = {
                        val currentGameInfos = it.value
                        if(currentGameInfos!=null && currentGameInfos is GameInfos)
                            _mutableGameInfos.value = currentGameInfos
                    },
                    onCancelled = {
                        switchScreen(ScreenName.Error)
                    })

                watchPlayers()
                watchScreen()
            }
        } catch (e: Exception) {
            switchScreen(ScreenName.Error)
        }
    }

    fun startGame() {

        switchScreen(ScreenName.Dashboard)

        val quiz = getRandomQuiz()!!
        //TODO: Da implementare
    }

    fun getRandomQuiz() : Quiz? {
        val totNum = runBlocking {
            _dbManager.readData("quiz/totNum", Int::class.java)
        }
        val quizId = MyRandom.int(0 until totNum!!)
        Log.d("TestQuiz", "Tot Quiz: $totNum, Range: ${0 until totNum!!}, Id: $quizId")
        return runBlocking {
            _dbManager.readData("quiz/$quizId", Quiz::class.java)
        }
    }

    //endregion

    //-------------------- private methods GameManager
    //region Private Methods

    private fun watchPlayers() {

        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing match Id")

        /*
        val playersListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentPlayers = snapshot.value ?: return

                if(currentPlayers !is Map<*, *>)
                    throw RuntimeException("Error occurred reading Players data structure in Database")

                _mutablePlayers.value = currentPlayers as Map<String, Player>
            }

            override fun onCancelled(error: DatabaseError) {
                switchScreen(ScreenName.Error)
            }
        }

        _dbManager.addListener("$id/players", playersListener)

         */
        _dbManager.addListener("$id/players", "watchPlayers",
            onDataChange = {
                val currentPlayers = it.value
                if(currentPlayers!=null){
                    if(currentPlayers !is Map<*, *>)
                        throw RuntimeException("Error occurred reading Players data structure in Database")

                    _mutablePlayers.value = currentPlayers as Map<String, Player>
                }
            },
            onCancelled = {
                switchScreen(ScreenName.Error)
            }
        )
    }



    //funzione che osserva il child "screen" della struttura firebase della partita
    //in base al valore di "screen" fa apparire la relativa schermata
    private fun watchScreen() {

        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing match Id")

        /*
        val screenListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                switchScreen(ScreenName.routeToScreenName(snapshot.value?.toString()?: ""))
            }

            override fun onCancelled(error: DatabaseError) {
                switchScreen(ScreenName.Error)
            }
        }

        _dbManager.addListener("$id/screen", screenListener)

         */
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

    //endregion
}