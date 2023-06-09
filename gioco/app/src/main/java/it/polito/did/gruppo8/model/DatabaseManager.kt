package it.polito.did.gruppo8.model

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class DatabaseManager() {

    private val _url = "https://gioco8-32e43-default-rtdb.europe-west1.firebasedatabase.app/"
    private val _firebase = Firebase.database(_url)
    private val _auth = Firebase.auth

    private val _dbScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    private var _listenerRegister: MutableMap<String, MutableMap<String, ValueEventListener>> = mutableMapOf()

    /**
     * Authenticates anonymously to the database.
     */
    fun authenticate(){
        //_firebase.setLogLevel(Logger.Level.DEBUG)
        _dbScope.launch {
            _auth.signInAnonymously().await()
            Log.d("DatabaseManager", "Current User: ${_auth.uid}")
        }
    }

    /**
     * Retrieves the ID of the current user authenticated to the database.
     *
     * @return a String with the user ID, that can be null if there's no user authenticated.
     */
    fun getCurrentUserID(): String? {
        return _auth.uid
    }

    /**
     * Checks if a path of the database contains any data.
     *
     * @param path the path to check.
     *
     * @return true if data are present, false if not.
     */
    suspend fun isDataPresent(path: String): Boolean{
        return _firebase.getReference(path).get().await().value != null
    }

    // Reference for read/write operations: https://firebase.google.com/docs/database/android/read-and-write -Mattia
    /**
     * Writes a data of type T into the database, at a specific path.
     *
     * Note: in order to properly serialize data, type T must be a class with a default constructor
     * that takes no arguments and no private fields.
     *
     * @param path the destination path to write.
     * @param data the data to write.
     * @param T the data type.
     */
    fun <T> writeData(path: String, data: T){
        _dbScope.launch{
            _firebase.getReference(path).setValue(data)
                .addOnSuccessListener {
                    Log.d("DatabaseManager", "Data $data written at path $path")
                }
                .addOnFailureListener{
                    Log.d("DatabaseManager", "ERROR: Could not write Data $data at path $path")
                }
                .await()
        }
    }

    fun removeData(path: String){
        _dbScope.launch{
            _firebase.getReference(path).removeValue()
                .addOnSuccessListener {
                    Log.d("DatabaseManager", "Data removed at path $path")
                }
                .addOnFailureListener{
                    Log.d("DatabaseManager", "ERROR: Could not remove data at path $path")
                }
                .await()
        }
    }

    /**
     * Retrieves the DataSnapshot from the database at specific path.
     *
     * @param path the path to read in the database.
     *
     * @return DataSnapshot at the specific path.
     */
    suspend fun getDataSnapshot(path: String): DataSnapshot {
        return _firebase.getReference(path).get()
            .addOnSuccessListener {
                Log.d("DatabaseManager", "Retrieved Snapshot ${it} from path $path")
            }
            .addOnFailureListener {
                Log.d("DatabaseManager", "ERROR: Could not read from path $path")
            }
            .await()
    }

    /**
     * Reads a specific data once from the database.
     *
     * @param path the data to read with its path in the database.
     * @param valueType the class of the data requested (T.class).
     * @param T the data type.
     *
     * @return n object of type T that can be null if the read operation has failed.
     */
    suspend fun <T> readData(path: String, valueType: Class<T>): T? {
        return _firebase.getReference(path).get()
                .addOnSuccessListener {
                    Log.d("DatabaseManager", "Data ${it.value} read from path $path")
                }
                .addOnFailureListener {
                    Log.d("DatabaseManager", "ERROR: Could not read from path $path")
                }
                .await().getValue(valueType)
    }

    /**
     * Subscribes a ValueEventListener to the data located in a specific path of the database.
     *
     * @param path the path of the target data.
     * @param listenerId an identifier for the listener that is going to be subscribed.
     * @param onDataChange the callback called every time the data at path changes.
     * @param onCancelled the callback called if the data at path is deleted.
     */
    /*
    fun addListener(path: String, listener: ValueEventListener){
        _firebase.getReference(path).addValueEventListener(listener)

        //Update listener register
        _listenerRegister.putIfAbsent(path, mutableListOf())
        _listenerRegister[path]!!.add(listener)
    }
     */
    fun addListener(path: String,
                    listenerId: String,
                    onDataChange: (snapshot: DataSnapshot)->Unit,
                    onCancelled: (error: DatabaseError)->Unit)
    {
        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) = onDataChange(snapshot)
            override fun onCancelled(error: DatabaseError) = onCancelled(error)
        }

        val ref = _firebase.getReference(path)
        ref.addValueEventListener(listener)
        Log.d("DatabaseManager", "Listener $listenerId subscribed to $ref")

        //Update listener register
        _listenerRegister.putIfAbsent(ref.toString(), mutableMapOf())
        _listenerRegister[ref.toString()]!!.putIfAbsent(listenerId, listener)
    }

    fun removeListener(path: String, listenerId: String){
        val ref = _firebase.getReference(path)

        val map = _listenerRegister[ref.toString()] ?: return
        if(map.contains(listenerId)){
            ref.removeEventListener(map[listenerId]!!)

            //Update listener register
            map.remove(listenerId)
            if(map.isEmpty())
                _listenerRegister.remove(ref.toString())
        }
    }

    fun removeAllListeners(path: String){
        val ref = _firebase.getReference(path)

        val map = _listenerRegister[ref.toString()] ?: return
        for (entry in map){
            ref.removeEventListener(entry.value)

            //Update listener register
            map.remove(entry.key)
        }
        _listenerRegister.remove(ref.toString())
    }
}