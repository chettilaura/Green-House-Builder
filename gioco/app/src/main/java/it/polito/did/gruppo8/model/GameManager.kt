package it.polito.did.gruppo8.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

import it.polito.did.gruppo8.ScreenName
import it.polito.did.gruppo8.model.baseClasses.*

class GameManager(private val scope: CoroutineScope) {

    //----------------def variabili GameManager
    //MVVM __ view(screen.kt)-viewmodel(gameviewmodel)-model(database->gamemanager)


    //screeName è un liveData -> contiene mutableScreenName che è un MutableLiveData
    //mutableScreenName contiene il nome dello screen corrente
    private val _mutableScreenName = MutableLiveData<ScreenName>().also {
        //prima assegnazione dello screen (fatta di default) è lo Splash
        //qui uso 'it.value' anzichè 'mutableScreenName.value' perché l'ho definito qui
        it.value = ScreenName.Splash
    }
    val screenName: LiveData<ScreenName> = _mutableScreenName


    //matchId è un liveData -> contiene mutableMatchId che è un MutableLiveData
    //mutableMatchId contiene l'ID della partita
    private val _mutableMatchId = MutableLiveData<String>()
    val matchId: LiveData<String> = _mutableMatchId

    // TODO: Rework players with Player class (no teams?)

    //players è un liveData -> contiene mutablePlayers che è un MutableLiveData
    //mutablePlayers contiene elenco (Map) dei giocatori (string,string)-> (authId, team)

    /*
    private val _mutablePlayers = MutableLiveData<Map<String, String>>().also {
        it.value = emptyMap()
    }
    val players: LiveData<Map<String, String>> = _mutablePlayers
     */

    private val _mutablePlayers = MutableLiveData<Map<String, Player>>().also{
        it.value = emptyMap()
    }
    val players: LiveData<Map<String, Player>> = _mutablePlayers


    //qui creo la referenza al DataBase tramite URL
    /*
    private val URL = "https://gioco8-32e43-default-rtdb.europe-west1.firebasedatabase.app/"
    private val firebase = Firebase.database(URL)
    private val firebaseAuth = Firebase.auth
    */
    private val _dbManager: DatabaseManager = DatabaseManager(scope)



    //--------------------funzione init



    init {
        //firebase.setLogLevel(Logger.Level.DEBUG)

        /*

        scope.launch {
            try {
                //accedo al DB autenticandomi
                firebaseAuth.signInAnonymously().await()
                Log.d("GameManager", "Current User: ${firebaseAuth.uid}")
                delay(500)
                //assegno come screen-corrente InitialScreen
                mutableScreenName.value = ScreenName.Initial
            } catch (e: Exception) {
                //assegno come screen-corrente Error
                mutableScreenName.value = ScreenName.Error(e.message ?: "Unknown error")
            }
        }

         */

        // DatabaseManager version
        scope.launch {
            try {
                _dbManager.authenticate()
                delay(500)
                _mutableScreenName.value = ScreenName.Initial
            } catch (e: Exception) {
                _mutableScreenName.value = ScreenName.Error(e.message ?: "Unknown error")
            }
        }
    }


    //--------------------inizio funzioni GameManager

    /*      MALNATI
    private fun assignTeam(players: Map<String,String>): Map<String,String>? {
        val teams = players.keys.groupBy { players[it].toString() }
        val teamNames = listOf("team1", "team2", "team3", "team4")
        val sizes = teamNames.map{ teams[it]?.size ?: 0 }
        val min: Int = sizes.stream().min(Integer::compare).get()
        var index = sizes.indexOf(min)
        val updatedPlayers = players.toMutableMap()
        var changed = false
        teams[""]?.forEach {
            updatedPlayers[it] = teamNames[index]
            index = (index +1) % teamNames.size
            changed = true
        }
        //ritorna updatedPlayers che contiene il nome del team assegnato
        return if (changed) updatedPlayers else null
    }
     */

    //versione Malnati: assegna al player che ha fatto join game il team corrispondente
    private fun watchPlayers() {
        /*
        //leggo dal livedata l'ID della partita
        val id = matchId.value ?: throw RuntimeException("Missing match Id")
        //cerco nel DB il puntatore con il nome della partita cercata
        val ref = firebase.getReference(id)

        //presa le reference della partita nel DB, si mette in ascolto sul child "players" della struttura
        //qui lettura da firebase fatta con addValueEventListener
        // la funzione addValueEventListener prevede override delle due funzioni "onDataChange" & "onCancelled"
        ref.child("players").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) { //snapshot è puntatore a reference nel database (posso fare snapshot.child() etc.)
                val v = snapshot.value
                if (v!=null && v is Map<*, *>) {
                    val updatedPlayers = assignTeam(v as Map<String,String>)
                    if (updatedPlayers != null) {
                        //qui riempie il child Players con il nome del team
                        ref.child("players").setValue(updatedPlayers)
                    } else {
                        mutablePlayers.value = v
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                mutableScreenName.value = ScreenName.Error(error.message)
            }
        })
         */

        //DatabaseManager version
        val id = matchId.value ?: throw RuntimeException("Missing match Id")

        val playersListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val currentPlayers = snapshot.value ?: return

                if(currentPlayers !is Map<*, *>)
                    throw RuntimeException("Error occurred reading Players data structure in Database")

                /*      MALNATI
                val updatedPlayers = assignTeam(currentPlayers as Map<String, String>)
                if(updatedPlayers != null)
                    _dbManager.writeData("$id/players", updatedPlayers)
                else
                    _dbManager.writeData("$id/players", currentPlayers)
                 */

                _mutablePlayers.value = currentPlayers as Map<String, Player>
            }

            override fun onCancelled(error: DatabaseError) {
                _mutableScreenName.value = ScreenName.Error(error.message)
            }
        }

        _dbManager.addListener("$id/players", playersListener)
    }

    /*      MALNATI
    private fun getMyTeam(): String {
        /*
        Log.d("GameManager", "players: ${players.value}")
        Log.d("GameManager", "uid: ${firebaseAuth.uid}")
        return players.value?.get(firebaseAuth.uid) ?: ""
        */

        //DatabaseManager version
        val uid = _dbManager.getCurrentUserID()
        Log.d("GameManager", "players: ${players.value}")
        Log.d("GameManager", "uid: $uid")
        return players.value?.get(uid) ?: ""
    }

     */

    //funzione che parte dopo aver premuto "Start new match" in InitialScreen
    fun createNewGame() {

        /*
        scope.launch {
            try {

                //crea nel DB un puntatore a "struttura partita" specificandone un nome
                val ref = firebase.getReference("game_Id_Prova")

                //se vado a vedere nel logcat mi mostra "creating match -MatchName-"
                Log.d("GameManager", "Creating match ${ref.key}")

                //riempie la "struttura partita" con info date/owner/screen -> saranno i suoi child
                ref.setValue(
                    mapOf(
                        "date" to LocalDateTime.now().toString(),
                        "owner" to firebaseAuth.uid,
                        "screen" to "WaitingStart"
                    )
                ).await()
                //messaggio sulla console
                Log.d("GameManager", "Match creation succeeded")

                //riempie la variabile MatchId
                mutableMatchId.value = ref.key
                Log.d("GameManager", "Match ID = ${mutableMatchId.value}")
                //dopo aver premuto "Start new match" va alla schermata "SetUpMatchScreen" che crea il codice QR
                mutableScreenName.value = ScreenName.SetupMatch(ref.key!!)
                //chiama la funzione "watchPlayers"
                watchPlayers()
            } catch (e:Exception) {
                mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
            }
        }

         */

        // DatabaseManager version
        val gameSessionId = "test_game_session"
        try {
            _dbManager.writeData(gameSessionId,
                mapOf(
                    "date" to LocalDateTime.now().toString(),
                    "hostId" to _dbManager.getCurrentUserID(),
                    "screen" to "WaitingStart"
                )
            )
            Log.d("GameManager", "Match creation succeeded")

            _mutableMatchId.value = gameSessionId
            _mutableScreenName.value = ScreenName.SetupMatch(gameSessionId)

            watchPlayers()
        } catch (e: Exception) {
            _mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
        }
    }



    //funzione che osserva il child "screen" della struttura firebase della partita
    //in base al valore di "screen" fa apparire la relativa schermata
    private fun watchScreen() {
        /*
        val id = matchId.value ?: throw RuntimeException("Missing match Id")
        val ref = firebase.getReference(id)

        //presa le reference della partita nel DB, si mette in ascolto sul child "screen" della struttura in firebase
        ref.child("screen").addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    //legge valore scritto nel child "screen" in firebase a mostra la relativa schermata
                    mutableScreenName.value = getScreenName(snapshot.value?.toString()?: "")
                }

                override fun onCancelled(error: DatabaseError) {
                    mutableScreenName.value = ScreenName.Error(error.message)
                }
            }
        )

         */

        //DatabaseManager version
        val id = matchId.value ?: throw RuntimeException("Missing match Id")

        val screenListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                _mutableScreenName.value = getScreenName(snapshot.value?.toString()?: "")
            }

            override fun onCancelled(error: DatabaseError) {
                _mutableScreenName.value = ScreenName.Error(error.message)
            }
        }

        _dbManager.addListener("$id/screen", screenListener)
    }

    //si riferisce al terzo elemento della "struttura partita" nel DB
    private fun getScreenName(name:String): ScreenName {
        return when (name) {
            "WaitingStart" -> ScreenName.WaitingStart
            //playing corrisponde a "PlayerScreen" ed esce una volta fatto il join game
            "Playing" -> ScreenName.Playing()
            else -> ScreenName.Error("Unknown screen $name")
        }
    }

    //questa funzione rimanda alla pagina 3A (file pdf di tutte le schermate)
    fun preJoinGame(){
       _mutableScreenName.value = ScreenName.Join
    }

    fun joinGame(matchId:String, nickname:String) {
        /*
        if (matchId.isEmpty()) return
        scope.launch {
            try {
                val ref = firebase.getReference(matchId)

                //se vado a vedere nel logcat mi mostra "joined match matchName"
                Log.d("GameManager", "joined match ${ref.key}")

                //val data = ref.get().await()
                if (ref.get().await() != null) {
                    mutableMatchId.value = matchId

                    //aggiunge nel DB a "struttura partita" un child "players"
                    //avente a sua volta un sotto-child con l'id di autentificazione firebase
                    ref
                        .child("players")
                        .child(firebaseAuth.uid!!)
                        .setValue("").await()

                    //da watchPlayers entra in AssignTeam e riempie il sotto-child con l'id di autentificazione firebase con il team
                    watchPlayers()
                    //chiama watchScreen che carica la schermata contenuta nel child della reference firebase
                    watchScreen()
                } else {
                    mutableScreenName.value = ScreenName.Error("Invalid gameId")
                }
            } catch (e: Exception) {
                mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
            }
        }
        */

        //DatabaseManager version
        try {
            if (matchId.isEmpty()) return

            val playerId = _dbManager.getCurrentUserID()
            if(playerId == null)
                _mutableScreenName.value = ScreenName.Error("User not authenticated to the database")
            Log.d("GameManager","joinGame: playerId is $playerId")

            /*
            //TODO: Do more test on blocking function isDataPresent
            if(!_dbManager.isDataPresent(matchId)) {
                _mutableScreenName.value = ScreenName.Error("Invalid gameId")
            }
            */

            _mutableMatchId.value = matchId
            Log.d("GameManager","joinGame: matchId is $matchId")

            val playerObj = playerId?.let { Player(it, nickname) }

            _dbManager.writeData("$matchId/players/$playerId", playerObj)

            Log.d("GameManager","joinGame: player data written")
            watchPlayers()
            watchScreen()
        } catch (e: Exception) {
            _mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
        }
    }



    fun startGame() {
        /*
        scope.launch {
            try {
                val ref = firebase.getReference(matchId.value ?: throw RuntimeException("Invalid State"))
                //cambia il valore del child "screen" nella "struttura partita" -> da "waitingStart" a "Playing"
                //la funzione "watchscreen" poi va a leggere questo valore per capire quale schermata caricare
                ref.child("screen").setValue("Playing").await()
                //passa al DashboardScreen
                mutableScreenName.value = ScreenName.Dashboard
                Log.d("GameManager", "Game started")
            } catch (e: Exception) {
                mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
            }
        }
        */

        //DatabaseManager version
        val id = matchId.value ?: throw RuntimeException("Missing match Id")

        try {
            _dbManager.writeData("$id/screen", "Playing")
            _mutableScreenName.value = ScreenName.Dashboard
        }
        catch (e: Exception) {
            _mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
        }
    }
}