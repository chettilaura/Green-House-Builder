package it.polito.did.gruppo8.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
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

class GameManager(private val scope: CoroutineScope/*, navController: NavController*/) {

    //region Fields

    /*
    private val _mutableCurrentScreenName = MutableLiveData<ScreenName>().also {
        it.value = ScreenName.Splash
    }
    val currentScreenName: LiveData<ScreenName> = _mutableCurrentScreenName
*/
    private val _mutableLobbyId = MutableLiveData<String>()
    val lobbyId: LiveData<String> = _mutableLobbyId

    private val _mutableCityName = MutableLiveData<String>()
    val cityName: LiveData<String> = _mutableCityName

    private val _mutablePlayers = MutableLiveData<Map<String, Player>>().also{
        it.value = emptyMap()
    }
    val players: LiveData<Map<String, Player>> = _mutablePlayers

    private val _dbManager: DatabaseManager = DatabaseManager(scope)

    //endregion

    //--------------------funzione init

    init {
        // DatabaseManager version
        scope.launch {
            try {
                _dbManager.authenticate()
                delay(500)
                //_mutableCurrentScreenName.value = ScreenName.Initial
                //switchScreen(ScreenName.MainMenu)
                Navigator.navigateTo(ScreenName.MainMenu)
            } catch (e: Exception) {
                //_mutableCurrentScreenName.value = ScreenName.Error(e.message ?: "Unknown error")
                //switchScreen(ScreenName.Error(e.message ?: "Unknown error"))
                //navController.navigate(ScreenName.Error.route)
                Navigator.navigateTo(ScreenName.Error)
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
    /*
    fun switchScreen(screenName: ScreenName, updateDatabase: Boolean = false, navController: NavController){
        try {
            if(updateDatabase){
                val id = lobbyId.value ?: throw RuntimeException("Missing match Id")
                //_dbManager.writeData("$id/screen", ScreenName.screenNameToString(screenName))
            }
            _mutableCurrentScreenName.value = screenName
        }
        catch (e:Exception){
            //switchScreen(ScreenName.Error(e.message ?: "Generic error"))
            //navController.navigate(ScreenName.Error.route)
            navigator.navigateTo(ScreenName.Error)
        }
    }

     */

    //funzione che parte dopo aver premuto "Start new match" in InitialScreen
    fun createNewGame(cityName: String/*, navController: NavController*/) {

        Log.d("createNewGame", "city name: $cityName")
        // DatabaseManager version
        //TODO: Randomizzare la generazione del gameSessionId (stringa alfanumerica di 5-6 caratteri) -Mattia
        val gameSessionId = "test_game_session"
        try {
            _dbManager.writeData(gameSessionId,
                mapOf(
                    "date" to LocalDateTime.now().toString(),
                    "hostId" to _dbManager.getCurrentUserID(),
                    "screen" to "WaitingStart",
                    "cityName" to cityName
                )
            )
            Log.d("GameManager", "Match creation succeeded")

            _mutableLobbyId.value = gameSessionId
            _mutableCityName.value = cityName

            //_mutableCurrentScreenName.value = ScreenName.SetupMatch(gameSessionId)
            //switchScreen(ScreenName.GameSetup)
            //navController.navigate(ScreenName.GameSetup.route)
            Navigator.navigateTo(ScreenName.GameSetup)


            watchPlayers()
        } catch (e: Exception) {
            //_mutableCurrentScreenName.value = ScreenName.Error(e.message ?: "Generic error")
            //switchScreen(ScreenName.Error(e.message ?: "Generic error"))
            //navController.navigate(ScreenName.Error.route)
            Navigator.navigateTo(ScreenName.Error)
        }
    }

    fun joinGame(matchId:String, nickname:String/*, navController: NavController*/) {

        //DatabaseManager version
        try {
            if (matchId.isEmpty()) return

            val playerId = _dbManager.getCurrentUserID()
                ?: throw RuntimeException("User not authenticated to the database")
            Log.d("GameManager","joinGame: playerId is $playerId")

            /*
            //TODO: Do more test on blocking function isDataPresent -Mattia
            if(!_dbManager.isDataPresent(matchId)) {
                throw RuntimeException("Invalid gameId")
            }
            */

            _mutableLobbyId.value = matchId
            Log.d("GameManager","joinGame: matchId is $matchId")

            val playerObj = Player(playerId, nickname)

            _dbManager.writeData("$matchId/players/$playerId", playerObj)

            Log.d("GameManager","joinGame: player data written")
            watchPlayers()
            //watchScreen()
        } catch (e: Exception) {
            //_mutableCurrentScreenName.value = ScreenName.Error(e.message ?: "Generic error")
            //switchScreen(ScreenName.Error(e.message ?: "Generic error"))
            //navController.navigate(ScreenName.Error.route)
            Navigator.navigateTo(ScreenName.Error)
        }
    }

    fun startGame(/*navController: NavController*/) {

        //DatabaseManager version
        /*
        val id = lobbyId.value ?: throw RuntimeException("Missing match Id")

        try {
            _dbManager.writeData("$id/screen", "Playing")
            _mutableCurrentScreenName.value = ScreenName.Dashboard
        }
        catch (e: Exception) {
            _mutableCurrentScreenName.value = ScreenName.Error(e.message ?: "Generic error")
        }
         */
        //switchScreen(ScreenName.Dashboard, true)
        //navController.navigate(ScreenName.Dashboard.route)
        Navigator.navigateTo(ScreenName.Dashboard)
    }

    //endregion

    //-------------------- private methods GameManager
    //region Private Methods

    private fun watchPlayers(/*navController: NavController*/) {

        //DatabaseManager version
        val id = lobbyId.value ?: throw RuntimeException("Missing match Id")

        val playersListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentPlayers = snapshot.value ?: return

                if(currentPlayers !is Map<*, *>)
                    throw RuntimeException("Error occurred reading Players data structure in Database")

                _mutablePlayers.value = currentPlayers as Map<String, Player>
            }

            override fun onCancelled(error: DatabaseError) {
                //switchScreen(ScreenName.Error(error.message))
                //navController.navigate(ScreenName.Error.route)
                Navigator.navigateTo(ScreenName.Error)
            }
        }

        _dbManager.addListener("$id/players", playersListener)
    }



    //funzione che osserva il child "screen" della struttura firebase della partita
    //in base al valore di "screen" fa apparire la relativa schermata
    /*
    private fun watchScreen() {

        //DatabaseManager version
        val id = lobbyId.value ?: throw RuntimeException("Missing match Id")

        val screenListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                switchScreen(ScreenName.stringToScreenName(snapshot.value?.toString()?: ""))
            }

            override fun onCancelled(error: DatabaseError) {
                switchScreen(ScreenName.Error(error.message))
            }
        }

        _dbManager.addListener("$id/screen", screenListener)
    }
    */

    /*  MALNATI, funzionalità spostata come metodo statico nella classe ScreenName -Mattia

    private fun getScreenName(name:String): ScreenName {
        return when (name) {
            "WaitingStart" -> ScreenName.WaitingStart
            //playing corrisponde a "PlayerScreen" ed esce una volta fatto il join game
            "Playing" -> ScreenName.Playing
            else -> ScreenName.Error("Unknown screen $name")
        }
    }
     */

    //endregion
}