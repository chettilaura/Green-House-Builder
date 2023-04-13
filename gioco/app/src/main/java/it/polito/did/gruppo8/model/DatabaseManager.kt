package it.polito.did.gruppo8.model

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class DatabaseManager(private val scope: CoroutineScope) {

    private val _url = "https://gioco8-32e43-default-rtdb.europe-west1.firebasedatabase.app/"
    private val _firebase = Firebase.database(_url)
    private val _auth = Firebase.auth

    private var _listenerRegister: MutableMap<String, MutableList<ValueEventListener>> = mutableMapOf()

    /**
     * Authenticates anonymously to the database.
     */
    fun authenticate(){
        //_firebase.setLogLevel(Logger.Level.DEBUG)
        scope.launch {
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
    fun isDataPresent(path: String): Boolean{
        //TODO: More test to check if runBlocking generates unwanted behaviour -Mattia
        val result = runBlocking {
            _firebase.getReference(path).get().await() != null
        }
        return result
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
        scope.launch {
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

    /**
     * Reads a specific data once from the database.
     *
     * @param path the data to read with its path in the database.
     * @param valueType the class of the data requested (T.class).
     * @param T the data type.
     *
     * @return n object of type T that can be null if the read operation has failed.
     */
    fun <T> readData(path: String, valueType: Class<T>): T?{
        var value : T? = null
        runBlocking {
            _firebase.getReference(path).get()
                .addOnSuccessListener {
                    value = it.getValue(valueType)
                    Log.d("DatabaseManager", "Data $value read from path $path")
                }
                .addOnFailureListener{
                    Log.d("DatabaseManager", "ERROR: Could not read from path $path")
                }
                .await()
        }
        return value
    }

    //TODO: Still test all listeners methods -Mattia

    /**
     * Subscribes a ValueEventListener to the data located in a specific path of the database.
     *
     * @param path the path of the target data
     * @param listener a listener operation which implements ValueEventListener
     */
    fun addListener(path: String, listener: ValueEventListener){
        _firebase.getReference(path).addValueEventListener(listener)

        //Update listener register
        _listenerRegister.putIfAbsent(path, mutableListOf())
        _listenerRegister[path]!!.add(listener)
    }

    fun removeListener(path: String, listener: ValueEventListener){
        _firebase.getReference(path).removeEventListener(listener)

        //Update listener register
        val list = _listenerRegister[path]
        list?.remove(listener)
        if(list != null && list.isEmpty())
            _listenerRegister.remove(path)
    }

    fun removeAllListeners(path: String){
        val ref = _firebase.getReference(path)

        val list = _listenerRegister[path] ?: return
        for (listener in list){
            ref.removeEventListener(listener)

            //Update listener register
            _listenerRegister[path]!!.remove(listener)
        }
    }
}