package it.polito.did.gruppo8.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
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

    private val _mutableGameInfos = MutableLiveData<GameInfos>().also {
        it.value = GameInfos()
    }
    val gameInfos: LiveData<GameInfos> = _mutableGameInfos

    private val _mutablePlayers = MutableLiveData<MutableMap<String, Player>>().also{
        it.value = mutableMapOf()
    }
    val players: LiveData<MutableMap<String, Player>> = _mutablePlayers
    val myPlayerId: String
        get() {
            return _dbManager.getCurrentUserID()
                ?: throw RuntimeException("User not authenticated to the database")
        }

    private val _mutableShop = MutableLiveData<MutableMap<Int, Item>>().also{
        it.value = mutableMapOf()
    }
    val shop: LiveData<MutableMap<Int, Item>> = _mutableShop

    /*
    private val _mutableTimer = MutableLiveData<Timer>().also{
        it.value = Timer(0)
    }
    val timer: LiveData<Timer> = _mutableTimer
     */

    private val _dbManager: DatabaseManager = DatabaseManager()

    //endregion

    //--------------------funzione init

    init {
        scope.launch {
            try {
                _dbManager.authenticate()

                val shopSnapshot = async { _dbManager.getDataSnapshot("shop") }.await()
                retrieveShop(shopSnapshot)

                delay(500)
                switchScreen(ScreenName.MainMenu)
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
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
            switchScreen(ScreenName.Error)
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
                    "hostId" to myPlayerId,
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

    fun startGame(totalRounds: Int, turnTime: Int, quizTime: Int) {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        if(players.value!!.entries.size == 0)
            switchScreen(ScreenName.Error)

        _mutableGameInfos.value!!.totalRounds = totalRounds
        _mutableGameInfos.value!!.turnTime = turnTime
        _mutableGameInfos.value!!.quizTime = quizTime
        _mutableGameInfos.value!!.totalTurns = _mutablePlayers.value!!.entries.size

        //Update Game settings
        _dbManager.writeData("$id/gameInfos", gameInfos.value)

        observeGameInfos()

        //Subscribe to roundCounter to check if the game has reach the end
        observeRoundCounter()

        //Host switches to the Dashboard
        switchScreen(ScreenName.Dashboard)

        //Next turn
        nextTurn()
    }

    // TODO: Move Ranking to local side, because Databse doesn't preserve the order.
    fun rankPlayers(){
        // Sort players based on weighted average of its stats
        _mutablePlayers.value = _mutablePlayers.value!!.toList().sortedByDescending { (_, player) ->
            player.house.stats.weightedAverage()
        }.toMap().toMutableMap()

        Log.d("RankPlayers", "Players ranked")
        /*
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")
        _dbManager.writeData("$id/players", players.value)
         */
    }

    fun endGame(){
        rankPlayers()
        notifyEndGameToArduino()
        switchScreen(ScreenName.GameOver)
    }

    fun exitGame(){
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        scope.launch {
            val check = async { _dbManager.isDataPresent(id) }.await()

            //Return to Main Menu
            switchScreen(ScreenName.MainMenu)

            //Delete lobby from the database
            if(check) _dbManager.removeData(id)

            //Clear local livedata
            clearLiveData()
        }
    }
    //endregion

    //region Methods for Client/Player

    fun joinGame(lobbyId:String, nickname:String) {
        try {
            if (lobbyId.isEmpty()) return

            Log.d("GameManager","joinGame: playerId is $myPlayerId")

            scope.launch {
                //Check if matchId is correct
                val check = async { _dbManager.isDataPresent(lobbyId) }.await()
                if(!check) {
                    //TODO: riportare ad una schermata di errore per dire che la partita non esiste.
                    //throw RuntimeException("Invalid gameId")
                    switchScreen(ScreenName.MainMenu)
                    return@launch
                }
                Log.d("GameManager","joinGame: matchId is $lobbyId")

                //Write player data on the database
                val playerObj = Player(myPlayerId, nickname)
                _dbManager.writeData("$lobbyId/players/$myPlayerId", playerObj)
                Log.d("GameManager","joinGame: player data written")

                //First read of GameInfos
                async {
                    _mutableGameInfos.value =
                        _dbManager.readData("$lobbyId/gameInfos", GameInfos::class.java)
                }.await()

                //Retrieve shop
                val shopSnapshot = async { _dbManager.getDataSnapshot("shop") }.await()
                retrieveShop(shopSnapshot)

                observeLobbyId()
                observeGameInfos()
                observePlayers()

                //Subscribe to roundCounter to check if the game has reach the end
                observeRoundCounter()

                observeScreen()
            }
        } catch (e: Exception) {
            switchScreen(ScreenName.Error)
        }
    }

    fun verifyQuiz(quiz: Quiz, answer: Int){
        val isPlayerTurn = myPlayerId==gameInfos.value!!.currentPlayerId
        val result = quiz.verifyAnswer(answer)
        Log.d("VerifyQuiz", "Question: ${quiz.question}\n" +
                "Answer: $answer, Correct: ${quiz.correct}" +
                "Result: $result")

        // Notify to Arduino
        if(isPlayerTurn)
            notifyAnswerToArduino(result)

        // Switch to feedback screen and update wallet
        when(result){
            Quiz.Result.NotGiven -> {
                Log.d("VerifyQuiz", "Answer not given")
                switchScreen(ScreenName.NoAnswer)
            }

            Quiz.Result.Wrong -> {
                Log.d("VerifyQuiz", "Answer wrong!")
                if(!isPlayerTurn)
                    _mutablePlayers.value!![myPlayerId]!!.wallet.removeCoins(25)
                switchScreen(ScreenName.WrongAnswer)
            }

            Quiz.Result.Correct -> {
                Log.d("VerifyQuiz", "Answer correct!")
                _mutablePlayers.value!![myPlayerId]!!.wallet.addCoins(50)
                switchScreen(ScreenName.CorrectAnswer)
            }
        }

        scope.launch {
            delay(3500)

            if(!isPlayerTurn){
                Log.d("VerifyQuiz", "Going to wait for end of turn")
                switchScreen(ScreenName.Waiting)
            }
            else{
                when(result){
                    Quiz.Result.Correct -> {
                        Log.d("VerifyQuiz", "Player is gonna play its turn")
                        switchScreen(ScreenName.HouseOverview)
                    }
                    else -> {
                        Log.d("VerifyQuiz", "Player is gonna skip its turn")
                        nextTurn()
                    }
                }
            }
            /*
            if(result==Quiz.Result.Correct && isPlayerTurn) {
                // Switch to overview screen and play the turn
                Log.d("VerifyQuiz", "Player is gonna play its turn")
                switchScreen(ScreenName.HouseOverview)
            }
            else if(result==Quiz.Result.Wrong && isPlayerTurn) {
                // Skip directly to next turn
                Log.d("VerifyQuiz", "Player is gonna skip its turn")
                nextTurn()
            }
            else {

            }
            */
        }
    }

    fun buyItemFromShop(itemId: Int, free:Boolean = false)
    {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")
        val me = players.value!![myPlayerId] ?: throw NullPointerException("Can't find player data")
        val newItem = shop.value!![itemId] ?: throw NullPointerException("Can't find item requested in the shop")

        // Add item
        me.house.addItem(newItem)

        // Remove Coins
        if(!free)
            me.wallet.removeCoins(newItem.price)

        // Remove Item from the player shop
        _mutableShop.value!!.remove(newItem.id)

        // Update player and database
        _mutablePlayers.value!![myPlayerId] = me
        _dbManager.writeData("$id/players/$myPlayerId", players.value!![myPlayerId])
    }

    fun endTurn(){
        nextTurn()
    }

    //endregion

    //region Public shared Methods

    fun getRandomQuiz() : Quiz {
        val totNum = runBlocking {
            _dbManager.readData("quiz/totNum", Int::class.java)
        }
        val quizId = MyRandom.int(0 until totNum!!)
        Log.d("GameManager", "Tot Quiz: $totNum, Picked Quiz: $quizId")
        return runBlocking {
            _dbManager.readData("quiz/$quizId", Quiz::class.java) ?: throw RuntimeException("Can't read Quiz from database")
        }
    }

    //endregion

    //region Private shared Methods
    private fun observePlayers() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener("$id/players", "observePlayers",
            onDataChange = { snapshot ->
                val currentPlayers = snapshot.value as Map<*, *>?
                if(currentPlayers!=null){
                    //Log.d("WatchPlayers","Update: Found type ${currentPlayers.javaClass.kotlin} -> $currentPlayers")

                    //Properly converting snapshot into a MutableMap<String,Player>
                    val tempMap = mutableMapOf<String, Player>()
                    for (childSnapshot in snapshot.children) {
                        val playerId = childSnapshot.key
                        val player = childSnapshot.getValue(Player::class.java)
                        if(playerId!=null && player!=null)
                            tempMap[playerId] = player
                    }
                    _mutablePlayers.value = tempMap

                    Log.d("ObservePlayers", "Data updated: ${_mutablePlayers.value!!.entries}")
                }
            },
            onCancelled = {
                //switchScreen(ScreenName.Error)
                Log.d("ObservePlayers","Data cancelled at $id/players")
            }
        )
    }

    private fun observeScreen() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener("$id/screen", "observeScreen",
            onDataChange = {
                _mutableCurrentScreenName.value = ScreenName.routeToScreenName(it.value?.toString()?: "")
                Log.d("ObserveScreen","Data updated: ${currentScreenName.value}")
                switchScreen(_mutableCurrentScreenName.value ?: ScreenName.Error)
            },
            onCancelled = {
                //switchScreen(ScreenName.Error)
                Log.d("ObserveScreen","Data cancelled at $id/screen")
            }
        )
    }

    private fun observeGameInfos() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener("$id/gameInfos", "observeGameInfos",
            onDataChange = {
                val currentGameInfos = it.getValue(GameInfos::class.java)
                if(currentGameInfos!=null){
                    _mutableGameInfos.value = currentGameInfos
                    Log.d("ObserveGameInfos","Data updated: ${gameInfos.value.toString()}")
                }
            },
            onCancelled = {
                //switchScreen(ScreenName.Error)
                Log.d("ObserveGameInfos","Data cancelled at $id/gameInfos")
            }
        )
    }

    private fun observeRoundCounter() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener("$id/gameInfos/roundCounter", "observeRoundCounter",
            onDataChange = {snapshot ->
                val currentRoundCounter = snapshot.getValue(Int::class.java)
                Log.d("ObserveRoundCounter", "Next round")
                if(currentRoundCounter == gameInfos.value!!.totalRounds){
                    Log.d("ObserveRoundCounter", "End game reached")
                    endGame()
                }
            },
            onCancelled = {
                //switchScreen(ScreenName.Error, updateDatabase = true)
                Log.d("ObserveRoundCounter","Data cancelled at $id/gameInfos/roundCounter")
            }
        )
    }

    private fun observeLobbyId() {
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        _dbManager.addListener(id, "observeLobbyId",
            onDataChange = {
                scope.launch {
                    val check = async{_dbManager.isDataPresent(id)}.await()
                    if(check) return@launch
                    Log.d("ObserveLobbyId","Data cancelled at $id")
                    clearLiveData()
                    _dbManager.removeAllListeners("$id/screen")
                    switchScreen(ScreenName.MainMenu)
                }
            },
            onCancelled = {

            }
        )
    }

    private fun retrieveShop(snapshot: DataSnapshot){
        val shopArray = snapshot.value as ArrayList<*>?
            ?: throw NullPointerException("Can't retrieve Shop from Database")

        Log.d("GameManager", "Array test: ${shopArray[0]}")

        for(child in snapshot.children){
            val item = child.getValue(Item::class.java)
            if(item!=null){
                _mutableShop.value!![item.id] = item
                Log.d("GameManager", "Added item ${shop.value!![item.id]} to shop")
                Log.d("GameManager", "Test Item: Name=${item.name}, GreenModifier=(${item.greenModifier}, ${item.greenModifier.value})")
            }
        }
    }

    private fun nextTurn(){
        val id = gameInfos.value!!.lobbyId ?: throw RuntimeException("Missing game Id")

        // Update turn and round counter
        val cachedInfos = _mutableGameInfos.value!!
        cachedInfos.turnCounter++
        if(cachedInfos.turnCounter==players.value!!.entries.size){
            cachedInfos.turnCounter = 0
            cachedInfos.roundCounter++
            if(cachedInfos.roundCounter == cachedInfos.totalRounds){
                Log.d("GameManager", "END GAME")
                _mutableGameInfos.value = cachedInfos
                _dbManager.writeData("$id/gameInfos", gameInfos.value)
                return
            }
        }

        Log.d("GameManager", "Preparing ${cachedInfos.turnCounter} turn...")

        // Update next player id
        val newCurrentPlayer = players.value!!.entries.elementAtOrNull(cachedInfos.turnCounter)
            ?: throw RuntimeException("Errore nell'aggiornamento del prossimo player")
        cachedInfos.currentPlayerId = newCurrentPlayer.key
        _mutableGameInfos.value = cachedInfos

        // Update db
        _dbManager.writeData("$id/gameInfos", gameInfos.value)
        Log.d("GameManager", "Turn of ${players.value!![gameInfos.value!!.currentPlayerId]!!.nickname}")
        notifyNextTurnToArduino(players.value!![gameInfos.value!!.currentPlayerId]!!.nickname)

        // Coroutine to pass to the next quiz
        scope.launch {
            _dbManager.writeData("$id/screen", ScreenName.WaitingQuiz.route)
            delay(5000)
            _dbManager.writeData("$id/screen", ScreenName.Quiz.route)
        }
    }

    private fun clearLiveData() {
        _mutableGameInfos.value = GameInfos()
        _mutablePlayers.value = mutableMapOf()
        _mutableShop.value = mutableMapOf()
    }
    //endregion

    //region Arduino Methods
    private fun notifyAnswerToArduino(result: Quiz.Result){
        when(result){
            Quiz.Result.Correct -> _dbManager.writeData("arduino/rispostaQuiz", 0)
            Quiz.Result.Wrong -> _dbManager.writeData("arduino/rispostaQuiz", 1)
            Quiz.Result.NotGiven -> _dbManager.writeData("arduino/rispostaQuiz", 2)
        }
    }

    private fun notifyNextTurnToArduino(nickname: String){
        _dbManager.writeData("arduino/nomeTurno", nickname)
        _dbManager.writeData("arduino/cambioTurno", 1)
    }

    private fun notifyEndGameToArduino(){
        _dbManager.writeData("arduino/finePartita", 1)
    }
    //endregion
}