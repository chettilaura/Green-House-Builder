package it.polito.did.gruppo8

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime


// QUESTO COMMENTO SERVE PER TESTARE GITHUB - Mattia
class GameManager(private val scope:CoroutineScope) {
    //commento branch laura---
    //commento branch post merge con mattia
    //commento branch edo
    //commento branch gio

    //----------------def variabili GameManager
    //MVVM __ view(screen.kt)-viewmodel(gameviewmodel)-model(database->gamemanager)


    //screeName è un liveData -> contiene mutableScreenName che è un MutableLiveData
    //mutableScreenName contiene il nome dello screen corrente
    private val mutableScreenName = MutableLiveData<ScreenName>().also {
        //prima assegnazione dello screen (fatta di default) è lo Splash
        //qui uso 'it.value' anzichè 'mutableScreenName.value' perché l'ho definito qui
        it.value = ScreenName.Splash
    }
    val screenName: LiveData<ScreenName> = mutableScreenName


    //matchId è un liveData -> contiene mutableMatchId che è un MutableLiveData
    //mutableMatchId contiene l'ID della partita
    private val mutableMatchId = MutableLiveData<String>()
    val matchId: LiveData<String> = mutableMatchId


    //players è un liveData -> contiene mutablePlayers che è un MutableLiveData
    //mutablePlayers contiene elenco (map) dei giocatori (string,string)
    private val mutablePlayers = MutableLiveData<Map<String, String>>().also {
        it.value = emptyMap()
    }
    val players: LiveData<Map<String, String>> = mutablePlayers


    //qui creo la referenza al DataBase tramite URL
    private val URL = "https://gioco8-32e43-default-rtdb.europe-west1.firebasedatabase.app/"
    private val firebase = Firebase.database(URL)
    private val firebaseAuth = Firebase.auth





    //--------------------funzione init



    init {
        //firebase.setLogLevel(Logger.Level.DEBUG)
        scope.launch {
            try {
                // coroutine ->".await" è suspend fun
                firebaseAuth.signInAnonymously().await()
                Log.d("GameManager", "Current User: ${firebaseAuth.uid}")
                delay(500)
                //assegno come screen-corrente InitialScreen
                mutableScreenName.value = ScreenName.Initial
            } catch (e: Exception) {
                //assegno come screen-corrente Error
                mutableScreenName.value = ScreenName.Error(e.message?:"Unknown error")
            }
        }
    }



    //--------------------inizio funzioni GameManager

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
        return if (changed) updatedPlayers else null
    }

    private fun watchPlayers() {
        val id = matchId.value ?: throw RuntimeException("Missing match Id")
        val ref = firebase.getReference(id)
        ref.child("players").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val v = snapshot.value
                if (v!=null && v is Map<*, *>) {
                    val updatedPlayers = assignTeam(v as Map<String,String>)
                    if (updatedPlayers != null) {
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

    }

    private fun getMyTeam(): String {
        Log.d("GameManager", "players: ${players.value}")
        Log.d("GameManager", "uid: ${firebaseAuth.uid}")
        return players.value?.get(firebaseAuth.uid) ?: ""
    }

    //funzione che parte dopo aver premuto "Start new match" in InitialScreen
    fun createNewGame() {
        scope.launch {
            try {

            //qui crea una reference specificando un nome
            //use ".getReference" to access a location in the database and read or write data
            //accede alla reference "firebase" (creata prima) creando il percorso reference/abc
                val ref = firebase.getReference("game_Id_Prova")

            //qui crea una reference NON specificando un nome -> assegnato in automatico
            //This will generate a new push id and return a reference to the location with that id
                //val ref = firebase.reference.push()


                //se vado a vedere nel logcat mi mostra "creating match -MatchName-"
                Log.d("GameManager","Creating match ${ref.key}")

                //usa la ref creata sopra e la riempie con una struct date/owner/screen
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
                //dopo aver premuto "Start new match" va alla schermata "SetUpMatchScreen" che crea il codice QR
                mutableScreenName.value = ScreenName.SetupMatch(ref.key!!)
                //chiama la funzione "watchPlayers" che
                watchPlayers()
            } catch (e:Exception) {
                mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
            }
        }
    }




    private fun watchScreen() {
        val id = matchId.value ?: throw RuntimeException("Missing match Id")
        //".getReference" legge nel DB
        val ref = firebase.getReference(id)
        ref.child("screen").addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    mutableScreenName.value = getScreenName(snapshot.value?.toString()?: "")
                }

                override fun onCancelled(error: DatabaseError) {
                    mutableScreenName.value = ScreenName.Error(error.message)
                }
            }
        )

    }

    private fun getScreenName(name:String): ScreenName {
        return when (name) {
            "WaitingStart" -> ScreenName.WaitingStart
            "Playing" -> ScreenName.Playing(getMyTeam())
            else -> ScreenName.Error("Unknown screen $name")
        }
    }

    fun joinGame(matchId:String) {
        if (matchId.isEmpty()) return
        scope.launch {
            try {
                val ref = firebase.getReference(matchId)

                //se vado a vedere nel logcat mi mostra "creating match abc"
                Log.d("GameManager","joined match ${ref.key}")

                val data = ref.get().await()
                if (data!=null) {
                    mutableMatchId.value = matchId
                    ref
                        .child("players")
                        .child(firebaseAuth.uid!!)
                        .setValue("").await()
                    watchPlayers()
                    watchScreen()
                } else {
                    mutableScreenName.value = ScreenName.Error("Invalid gameId")
                }
            } catch (e: Exception) {
                mutableScreenName.value = ScreenName.Error(e.message?: "Generic error")
            }
        }
    }

    fun startGame() {
        scope.launch {
            try {
                val ref = firebase.getReference(matchId.value ?: throw RuntimeException("Invalid State"))
                ref.child("screen").setValue("Playing").await()
                mutableScreenName.value = ScreenName.Dashboard
                Log.d("GameManager", "Game started")
            } catch (e: Exception) {
                mutableScreenName.value = ScreenName.Error(e.message ?: "Generic error")
            }
        }
    }


}